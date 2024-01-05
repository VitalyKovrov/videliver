package com.virtaly.videliver.payment.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String mode;

    @Column
    private Long orderId;

    @Column
    private double amount;

    @Column
    private String status;
}