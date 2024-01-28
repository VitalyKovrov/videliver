package com.virtaly.videliver.payment.service;

import com.virtaly.videliver.payment.dto.CustomerOrder;
import com.virtaly.videliver.payment.exception.CustomerAvailableAmountNotEnoughException;
import com.virtaly.videliver.payment.exception.NoPaymentInfoForCustomerException;
import com.virtaly.videliver.payment.model.Customer;
import com.virtaly.videliver.payment.model.Payment;
import com.virtaly.videliver.payment.repository.CustomerRepository;
import com.virtaly.videliver.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    public Payment createPayment(CustomerOrder order) throws CustomerAvailableAmountNotEnoughException, NoPaymentInfoForCustomerException {
        Customer customer = customerRepository.findById(order.getCustomerId())
                .orElseThrow(() -> new NoPaymentInfoForCustomerException(order.getCustomerId()));
        if (order.getTotalPrice() < customer.getAvailableAmount()) {
            customer.setReservedAmount(customer.getReservedAmount() + order.getTotalPrice());
            customer.setAvailableAmount(customer.getAvailableAmount() - order.getTotalPrice());
        } else {
            throw new CustomerAvailableAmountNotEnoughException(order.getCustomerId(), order.getOrderId());
        }
        Payment payment = new Payment();
        payment.setCustomerId(customer.getId());
        payment.setAmount(order.getTotalPrice());
        payment.setMode(order.getPaymentMode());
        payment.setOrderId(order.getOrderId());
        payment.setStatus("SUCCESS");
        Payment savedPayment = paymentRepository.save(payment);
        customerRepository.save(customer);
        return savedPayment;
    }

    @Transactional
    public void reversePayment(CustomerOrder order) throws NoPaymentInfoForCustomerException {
        Customer customer = customerRepository.findById(order.getCustomerId())
                .orElseThrow(() -> new NoPaymentInfoForCustomerException(order.getCustomerId()));
        Iterable<Payment> payments = paymentRepository.findByOrderId(order.getOrderId());
        payments.forEach(p -> {
            customer.setReservedAmount(customer.getReservedAmount() - order.getTotalPrice());
            customer.setAvailableAmount(customer.getAvailableAmount() + order.getTotalPrice());
            customerRepository.save(customer);
            p.setStatus("FAILED");
            paymentRepository.save(p);
        });
    }
}
