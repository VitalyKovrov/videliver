package com.virtaly.videliver.customer.controller.dto;

import lombok.Data;

@Data
public class CreateCustomerRequest {
    private String firstName;
    private String lastName;
    private String email;
}
