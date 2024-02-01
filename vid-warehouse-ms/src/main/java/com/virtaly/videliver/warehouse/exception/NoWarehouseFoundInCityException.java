package com.virtaly.videliver.warehouse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoWarehouseFoundInCityException extends RuntimeException {
    public NoWarehouseFoundInCityException(String city) {
        super(String.format("No warehouse found in city %s", city));
    }
}
