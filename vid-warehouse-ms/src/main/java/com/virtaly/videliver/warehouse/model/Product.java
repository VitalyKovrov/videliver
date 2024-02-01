package com.virtaly.videliver.warehouse.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product")
@IdClass(ProductPk.class)
public class Product {

    @Id
    private long id;

    @Id
    private long warehouseId;

    @Column
    private int count;
}
