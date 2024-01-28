package com.virtaly.videliver.payment.repository;

import com.virtaly.videliver.payment.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
