package com.virtaly.videliver.order.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private long customerId;

    @Column
    private long productId;

    @Column
    private int productCount;

    @Column
    private double totalPrice;

    @Column
    private String status;
}
