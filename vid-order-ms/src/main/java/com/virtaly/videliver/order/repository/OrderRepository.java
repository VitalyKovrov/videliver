package com.virtaly.videliver.order.repository;

import com.virtaly.videliver.order.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
