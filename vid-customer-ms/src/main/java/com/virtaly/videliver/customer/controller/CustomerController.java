package com.virtaly.videliver.customer.controller;

import com.virtaly.videliver.customer.controller.dto.CreateCustomerRequest;
import com.virtaly.videliver.customer.controller.dto.CreateCustomerResponse;
import com.virtaly.videliver.customer.model.Customer;
import com.virtaly.videliver.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CreateCustomerResponse createCustomer(@RequestBody CreateCustomerRequest request) {
        log.info("POST /customers - Add a new customer");
        Customer result = customerService.create(request);
        return new CreateCustomerResponse(result.getId());
    }
}
