package com.virtaly.videliver.customer.repository;

import com.virtaly.videliver.customer.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
