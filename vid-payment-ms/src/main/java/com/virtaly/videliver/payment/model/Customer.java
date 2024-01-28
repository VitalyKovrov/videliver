package com.virtaly.videliver.payment.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Column
    private Long id;

    @Column
    private double availableAmount;

    @Column
    private double reservedAmount;
}
