package com.virtaly.videliver.warehouse.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtaly.videliver.warehouse.dto.InventoryEvent;
import com.virtaly.videliver.warehouse.dto.PaymentEvent;
import com.virtaly.videliver.warehouse.model.Product;
import com.virtaly.videliver.warehouse.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }

    @KafkaListener(topics = "new-payments", groupId = "payments-group")
    public void updateInventory(String paymentEvent) throws JsonProcessingException {
        log.info("Received event" + paymentEvent);
        PaymentEvent p = new ObjectMapper().readValue(paymentEvent, PaymentEvent.class);
        productService.updateProductForOrder(p.getOrder());
    }

    @KafkaListener(topics = "reversed-inventory", groupId = "inventory-group")
    public void reverseInventory(String event) throws JsonProcessingException {
        log.info("Received event" + event);
        InventoryEvent inventoryEvent = new ObjectMapper().readValue(event, InventoryEvent.class);
        productService.reverseInventoryForOrder(inventoryEvent.getOrder());
    }
}
