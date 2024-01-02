package com.virtaly.videliver.warehouse.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private int quantity;

    @Column
    private String item;
}
