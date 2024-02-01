package com.virtaly.videliver.warehouse.service;

import com.virtaly.videliver.warehouse.model.Warehouse;
import com.virtaly.videliver.warehouse.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;

    public List<Warehouse> getWarehousesInCity(String city) {
        return warehouseRepository.findAllByCity(city);
    }
}
