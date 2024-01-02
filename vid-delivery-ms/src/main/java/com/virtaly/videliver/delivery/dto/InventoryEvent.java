package com.virtaly.videliver.delivery.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventoryEvent {
    private String type;
    private CustomerOrder order;
}
