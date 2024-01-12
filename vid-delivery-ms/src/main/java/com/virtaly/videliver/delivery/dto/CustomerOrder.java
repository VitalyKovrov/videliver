package com.virtaly.videliver.delivery.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrder {
    String item;

    int quantity;

    double amount;

    String paymentMode;

    long orderId;

    String address;
}
