package com.virtaly.videliver.warehouse.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "warehouse")
public class Warehouse {

    @Id
    @Column
    private long id;

    @Column
    private String name;

    @Column
    private String city;
}
