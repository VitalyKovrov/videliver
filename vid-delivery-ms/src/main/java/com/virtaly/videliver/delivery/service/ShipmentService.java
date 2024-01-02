package com.virtaly.videliver.delivery.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtaly.videliver.delivery.dto.CustomerOrder;
import com.virtaly.videliver.delivery.dto.InventoryEvent;
import com.virtaly.videliver.delivery.model.Shipment;
import com.virtaly.videliver.delivery.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShipmentService {
    private final ShipmentRepository shipmentRepository;
    private final KafkaTemplate<String, InventoryEvent> kafkaInventoryTemplate;

    public Shipment createShipment(CustomerOrder order) {
        Shipment shipment = new Shipment();
        try {
            if (order.getAddress() == null) {
                throw new Exception("Address not present");
            }
            shipment.setAddress(order.getAddress());
            shipment.setOrderId(order.getOrderId());
            shipment.setStatus("success");
            shipmentRepository.save(shipment);
        } catch (Exception e) {
            shipment.setOrderId(order.getOrderId());
            shipment.setStatus("failed");
            shipmentRepository.save(shipment);

            InventoryEvent reverseEvent = InventoryEvent.builder()
                    .type("INVENTORY_REVERSED")
                    .order(order)
                    .build();
            this.kafkaInventoryTemplate.send("reversed-inventory", reverseEvent);
        }
        return shipment;
    }
}
