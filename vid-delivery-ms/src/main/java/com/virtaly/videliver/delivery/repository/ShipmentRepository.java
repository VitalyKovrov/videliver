package com.virtaly.videliver.delivery.repository;

import com.virtaly.videliver.delivery.model.Shipment;
import org.springframework.data.repository.CrudRepository;

public interface ShipmentRepository extends CrudRepository<Shipment, Long> {
}
