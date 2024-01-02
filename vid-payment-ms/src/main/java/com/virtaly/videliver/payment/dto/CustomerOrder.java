package com.virtaly.videliver.payment.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerOrder {
    String item;

    int quantity;

    double amount;

    String paymentMode;

    long orderId;

    String address;
}
