package com.virtaly.videliver.warehouse.repository;

import com.virtaly.videliver.warehouse.model.Inventory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InventoryRepository extends CrudRepository<Inventory, Long> {
    List<Inventory> findByProductId(long productId);
}
