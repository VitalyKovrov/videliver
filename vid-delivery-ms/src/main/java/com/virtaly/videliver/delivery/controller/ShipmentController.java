package com.virtaly.videliver.delivery.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtaly.videliver.delivery.dto.CustomerOrder;
import com.virtaly.videliver.delivery.dto.InventoryEvent;
import com.virtaly.videliver.delivery.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ShipmentController {
    private final ShipmentService shipmentService;

    @KafkaListener(topics = "new-inventory", groupId = "inventory-group")
    public void createShipment(String inventoryEvent) throws JsonProcessingException {
        log.info("Received event" + inventoryEvent);
        InventoryEvent i = new ObjectMapper().readValue(inventoryEvent, InventoryEvent.class);
        CustomerOrder order = i.getOrder();
        this.shipmentService.createShipment(order);
    }
}
