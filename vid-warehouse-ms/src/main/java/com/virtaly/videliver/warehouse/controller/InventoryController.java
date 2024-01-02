package com.virtaly.videliver.warehouse.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtaly.videliver.warehouse.dto.CustomerOrder;
import com.virtaly.videliver.warehouse.dto.InventoryEvent;
import com.virtaly.videliver.warehouse.dto.PaymentEvent;
import com.virtaly.videliver.warehouse.dto.Stock;
import com.virtaly.videliver.warehouse.model.Inventory;
import com.virtaly.videliver.warehouse.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController("/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping("/inventory")
    public void addInventory(@RequestBody Stock stock) {
        List<Inventory> items = inventoryService.findByItem(stock.getItem());
        if (items.iterator().hasNext()) {
            items.forEach(i -> {
                i.setQuantity(stock.getQuantity() + i.getQuantity());
                inventoryService.updateInventory(i);
            });
        } else {
            inventoryService.createInventory(stock);
        }
    }

    @KafkaListener(topics = "new-payments", groupId = "payments-group")
    public void updateInventory(String paymentEvent) throws JsonProcessingException {
        PaymentEvent p = new ObjectMapper().readValue(paymentEvent, PaymentEvent.class);
        inventoryService.updateInventoryForOrder(p.getOrder());
    }
}
