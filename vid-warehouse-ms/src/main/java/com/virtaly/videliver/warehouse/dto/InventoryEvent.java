package com.virtaly.videliver.warehouse.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventoryEvent {
    private String type;
    private CustomerOrder order;
}