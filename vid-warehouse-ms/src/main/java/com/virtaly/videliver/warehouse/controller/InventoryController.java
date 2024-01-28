package com.virtaly.videliver.warehouse.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtaly.videliver.warehouse.dto.InventoryEvent;
import com.virtaly.videliver.warehouse.dto.PaymentEvent;
import com.virtaly.videliver.warehouse.dto.Stock;
import com.virtaly.videliver.warehouse.model.Inventory;
import com.virtaly.videliver.warehouse.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping
    public void addInventory(@RequestBody Stock stock) {
        List<Inventory> items = inventoryService.findByProductId(stock.getProductId());
        if (items.iterator().hasNext()) {
            items.forEach(i -> {
                i.setQuantity(stock.getQuantity() + i.getQuantity());
                inventoryService.updateInventory(i);
            });
        } else {
            inventoryService.createInventory(stock);
        }
    }

    @GetMapping("/{productId}")
    public List<Inventory> getInventory(@PathVariable long productId) {
        return inventoryService.findByProductId(productId);
    }

    @KafkaListener(topics = "new-payments", groupId = "payments-group")
    public void updateInventory(String paymentEvent) throws JsonProcessingException {
        log.info("Received event" + paymentEvent);
        PaymentEvent p = new ObjectMapper().readValue(paymentEvent, PaymentEvent.class);
        inventoryService.updateInventoryForOrder(p.getOrder());
    }

    @KafkaListener(topics = "reversed-inventory", groupId = "inventory-group")
    public void reverseInventory(String event) throws JsonProcessingException {
        log.info("Received event" + event);
        InventoryEvent inventoryEvent = new ObjectMapper().readValue(event, InventoryEvent.class);
        inventoryService.reverseInventoryForOrder(inventoryEvent.getOrder());
    }
}
