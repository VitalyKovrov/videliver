package com.virtaly.videliver.customer.controller.dto;

import com.virtaly.videliver.common.model.PersonName;
import lombok.Data;

@Data
public class CreateCustomerRequest {
    private PersonName name;
}
