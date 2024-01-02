package com.virtaly.videliver.order.model;

import com.virtaly.videliver.order.controller.dto.CustomerOrder;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderEvent {
    private String type;
    private CustomerOrder order;
}
