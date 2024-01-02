package com.virtaly.videliver.warehouse.service;

import com.virtaly.videliver.warehouse.dto.Stock;
import com.virtaly.videliver.warehouse.model.Inventory;
import com.virtaly.videliver.warehouse.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public List<Inventory> findByItem(String item) {
        return inventoryRepository.findByItem(item);
    }

    public Inventory createInventory(Stock stock) {
        Inventory i = new Inventory();
        i.setItem(stock.getItem());
        i.setQuantity(stock.getQuantity());
        return inventoryRepository.save(i);
    }

    public Inventory updateInventory(Inventory i) {
        return inventoryRepository.save(i);
    }
}
