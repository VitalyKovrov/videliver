package com.virtaly.videliver.delivery.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtaly.videliver.delivery.dto.CustomerOrder;
import com.virtaly.videliver.delivery.dto.InventoryEvent;
import com.virtaly.videliver.delivery.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("/inventory")
@RequiredArgsConstructor
public class ShipmentController {
    private final ShipmentService shipmentService;

    @KafkaListener(topics = "new-inventory", groupId = "inventory-group")
    public void updateInventory(String paymentEvent) throws JsonProcessingException {
        InventoryEvent inventoryEvent = new ObjectMapper().readValue(paymentEvent, InventoryEvent.class);
        CustomerOrder order = inventoryEvent.getOrder();
        this.shipmentService.createShipment(order);
    }
}
