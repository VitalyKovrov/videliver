package com.virtaly.videliver.payment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoPaymentInfoForCustomerException extends Exception {
    public NoPaymentInfoForCustomerException(long customerId) {
        super(String.format("No payment info found for customer with id %s", customerId));
    }
}
