package com.virtaly.videliver.payment.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderEvent {
    private String type;
    private CustomerOrder order;
}
