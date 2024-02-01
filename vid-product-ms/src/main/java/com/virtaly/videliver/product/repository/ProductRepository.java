package com.virtaly.videliver.product.repository;

import com.virtaly.videliver.product.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAllByRetailerId(long retailerId);
}
