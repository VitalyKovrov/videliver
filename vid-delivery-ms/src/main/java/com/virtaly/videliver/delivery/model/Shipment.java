package com.virtaly.videliver.delivery.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "shipment")
public class Shipment {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String address;

    @Column
    private String status;

    @Column
    private long orderId;
}
