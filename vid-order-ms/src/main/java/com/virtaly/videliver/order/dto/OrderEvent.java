package com.virtaly.videliver.order.dto;

import com.virtaly.videliver.order.dto.CustomerOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent {
    private String type;
    private CustomerOrder order;
}
