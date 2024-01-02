package com.virtaly.videliver.payment.repository;

import com.virtaly.videliver.payment.model.Payment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
    List<Payment> findByOrderId(long orderId);
}
