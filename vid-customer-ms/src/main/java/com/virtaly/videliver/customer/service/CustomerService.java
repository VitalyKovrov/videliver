package com.virtaly.videliver.customer.service;

import com.virtaly.videliver.customer.controller.dto.CreateCustomerRequest;
import com.virtaly.videliver.customer.model.Customer;
import com.virtaly.videliver.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    public Customer create(CreateCustomerRequest createCustomerRequest) {
        return null;
    }
}
