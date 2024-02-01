package com.virtaly.videliver.warehouse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoProductsFoundInCityException extends RuntimeException {
    public NoProductsFoundInCityException(long productId, String city) {
        super(String.format("No products with id %s found in city %s", productId, city));
    }
}
