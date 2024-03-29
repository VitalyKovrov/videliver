package com.virtaly.videliver.payment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtaly.videliver.payment.dto.CustomerOrder;
import com.virtaly.videliver.payment.dto.OrderEvent;
import com.virtaly.videliver.payment.dto.PaymentEvent;
import com.virtaly.videliver.payment.exception.CustomerAvailableAmountNotEnoughException;
import com.virtaly.videliver.payment.exception.NoPaymentInfoForCustomerException;
import com.virtaly.videliver.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private final KafkaTemplate<String, PaymentEvent> kafkaPaymentTemplate;
    private final KafkaTemplate<String, OrderEvent> kafkaOrderTemplate;

    @KafkaListener(topics = "new-orders", groupId = "orders-group")
    public void processPayment(String event) throws JsonProcessingException {
        log.info("Received event" + event);
        OrderEvent orderEvent = new ObjectMapper().readValue(event, OrderEvent.class);
        CustomerOrder order = orderEvent.getOrder();
        try {
            paymentService.createPayment(order);
            PaymentEvent paymentEvent = PaymentEvent.builder()
                    .order(orderEvent.getOrder())
                    .type("PAYMENT_CREATED")
                    .build();
            kafkaPaymentTemplate.send("new-payments", paymentEvent);
        } catch (CustomerAvailableAmountNotEnoughException | NoPaymentInfoForCustomerException e) {
            log.error("Error while processing payment", e);
            OrderEvent oe = OrderEvent.builder()
                    .order(order)
                    .type("ORDER_REVERSED")
                    .build();
            kafkaOrderTemplate.send("reversed-orders", oe);
        }
    }

    @KafkaListener(topics = "reversed-payments", groupId = "payments-group")
    public void reversePayment(String event) {
        log.info("Received event" + event);
        try {
            PaymentEvent paymentEvent = new ObjectMapper().readValue(event, PaymentEvent.class);
            CustomerOrder order = paymentEvent.getOrder();
            paymentService.reversePayment(order);
            OrderEvent orderEvent = OrderEvent.builder()
                    .order(paymentEvent.getOrder())
                    .type("ORDER_REVERSED")
                    .build();
            this.kafkaOrderTemplate.send("reversed-orders", orderEvent);
        } catch (Exception e) {
            log.error("Error reversing payment", e);
        }

    }
}
