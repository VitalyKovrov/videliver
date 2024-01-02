package com.virtaly.videliver.warehouse.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Stock {
    private String item;
    private int quantity;
}
