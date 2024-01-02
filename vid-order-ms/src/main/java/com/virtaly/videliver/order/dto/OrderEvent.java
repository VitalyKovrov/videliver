package com.virtaly.videliver.order.dto;

import com.virtaly.videliver.order.dto.CustomerOrder;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderEvent {
    private String type;
    private CustomerOrder order;
}
