package com.virtaly.videliver.payment.service;

import com.virtaly.videliver.payment.dto.CustomerOrder;
import com.virtaly.videliver.payment.model.Payment;
import com.virtaly.videliver.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public Payment createPayment(CustomerOrder order) {
        Payment payment = new Payment();
        payment.setAmount(order.getAmount());
        payment.setMode(order.getPaymentMode());
        payment.setOrderId(order.getOrderId());
        payment.setStatus("SUCCESS");
        return paymentRepository.save(payment);
    }

    public Payment updatePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public List<Payment> findPayments(long orderId) {
        return paymentRepository.findByOrderId(orderId);
    }
}
