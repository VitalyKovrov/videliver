package com.virtaly.videliver.payment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerAvailableAmountNotEnoughException extends Exception {
    public CustomerAvailableAmountNotEnoughException(long customerId, long orderId) {
        super(String.format("Customer with id %s doesn't have available amount to pay for order with id %s", customerId, orderId));
    }
}
