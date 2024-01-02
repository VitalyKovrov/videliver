package com.virtaly.videliver.warehouse.service;

import com.virtaly.videliver.warehouse.dto.CustomerOrder;
import com.virtaly.videliver.warehouse.dto.InventoryEvent;
import com.virtaly.videliver.warehouse.dto.PaymentEvent;
import com.virtaly.videliver.warehouse.dto.Stock;
import com.virtaly.videliver.warehouse.model.Inventory;
import com.virtaly.videliver.warehouse.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final KafkaTemplate<String, InventoryEvent> kafkaInventoryTemplate;
    private final KafkaTemplate<String, PaymentEvent> kafkaPaymentTemplate;

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

    public void updateInventoryForOrder(CustomerOrder order) {
        try {
            Iterable<Inventory> inventories = inventoryRepository.findByItem(order.getItem());
            boolean exists = inventories.iterator().hasNext();
            if (!exists) {
                throw new Exception("Stock not available");
            }
            inventories.forEach(
                    i -> {
                        i.setQuantity(i.getQuantity() - order.getQuantity());
                        inventoryRepository.save(i);
                    });
            InventoryEvent event = InventoryEvent.builder()
                    .type("INVENTORY_UPDATED")
                    .order(order)
                    .build();
            kafkaInventoryTemplate.send("new-inventory", event);

        } catch (Exception e) {
            PaymentEvent pe = PaymentEvent.builder()
                    .order(order)
                    .type("PAYMENT_REVERSED")
                    .build();
            kafkaPaymentTemplate.send("reversed-payments", pe);
        }
    }
}
