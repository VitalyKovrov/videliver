package com.virtaly.videliver.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    long productId;
    long retailerId;
    String name;
    String description;
    double price;
    long totalCount;
}
