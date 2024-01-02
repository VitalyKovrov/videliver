package com.virtaly.videliver.order.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private String item;

    @Column
    private int quantity;

    @Column
    private double amount;

    @Column
    private String status;
}
