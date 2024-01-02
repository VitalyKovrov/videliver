package com.virtaly.videliver.order.controller;

import com.virtaly.videliver.order.dto.CustomerOrder;
import com.virtaly.videliver.order.model.Order;
import com.virtaly.videliver.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody CustomerOrder customerOrder) {
        return orderService.createOrder(customerOrder);
    }
}
