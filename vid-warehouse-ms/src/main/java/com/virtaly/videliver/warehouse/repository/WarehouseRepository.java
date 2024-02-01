package com.virtaly.videliver.warehouse.repository;

import com.virtaly.videliver.warehouse.model.Warehouse;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WarehouseRepository extends CrudRepository<Warehouse, Long> {
    List<Warehouse> findAllByCity(String city);
}
