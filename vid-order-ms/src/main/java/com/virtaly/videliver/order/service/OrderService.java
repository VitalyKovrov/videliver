package com.virtaly.videliver.order.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtaly.videliver.order.dto.CustomerOrder;
import com.virtaly.videliver.order.model.Order;
import com.virtaly.videliver.order.dto.OrderEvent;
import com.virtaly.videliver.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public Order createOrder(CustomerOrder customerOrder) {
        Order order = new Order();
        try {
            order.setAmount(customerOrder.getAmount());
            order.setItem(customerOrder.getItem());
            order.setQuantity(customerOrder.getQuantity());
            order.setStatus("CREATED");
            order = orderRepository.save(order);
            customerOrder.setOrderId(order.getId());
            OrderEvent event = OrderEvent.builder()
                    .type("ORDER_CREATED")
                    .order(customerOrder)
                    .build();
            this.kafkaTemplate.send("new-orders", event);
        } catch (Exception e) {
            order.setStatus("FAILED");
            this.orderRepository.save(order);
        }
        return order;
    }

    @KafkaListener(topics = "reversed-orders", groupId = "orders-group")
    public void reverseOrder(String event) {
        log.info("Received event" + event);
        try {
            OrderEvent orderEvent = new ObjectMapper().readValue(event, OrderEvent.class);
            Optional<Order> order = orderRepository.findById(orderEvent.getOrder().getOrderId());
            order.ifPresent(o -> {
                o.setStatus("FAILED");
                orderRepository.save(o);
            });
        } catch (Exception e) {
            log.error("Error while reversing order", e);
        }
    }

    public Order getOrder(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Order %s not found", id)));
    }
}
