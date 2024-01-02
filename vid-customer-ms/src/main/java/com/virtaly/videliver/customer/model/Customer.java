package com.virtaly.videliver.customer.model;

import com.virtaly.videliver.common.model.PersonName;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private PersonName name;

}
