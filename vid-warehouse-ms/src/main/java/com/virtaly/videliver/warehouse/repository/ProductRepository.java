package com.virtaly.videliver.warehouse.repository;

import com.virtaly.videliver.warehouse.model.Product;
import com.virtaly.videliver.warehouse.model.ProductPk;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, ProductPk> {
    List<Product> findAllByIdAndWarehouseIdInAndCountGreaterThanEqual(long productId, List<Long> warehouseIds, int minCount);
}
