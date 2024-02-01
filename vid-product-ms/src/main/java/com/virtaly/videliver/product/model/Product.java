package com.virtaly.videliver.product.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column
    private long id;

    @Column
    private long retailerId;

    @Column
    private String name;

    @Column
    private String description;

    private long totalCount;

    private Double price;
}
