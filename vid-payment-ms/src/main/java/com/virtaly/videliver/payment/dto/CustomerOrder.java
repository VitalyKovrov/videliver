package com.virtaly.videliver.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrder {

    long orderId;

    long customerId;

    long productId;

    int productCount;

    double totalPrice;

    String paymentMode;

    Address address;
}
