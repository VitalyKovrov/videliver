package com.virtaly.videliver.customer.model;

import com.virtaly.videliver.common.model.PersonName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
@Access(AccessType.FIELD)
public class Customer {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private PersonName name;

}
