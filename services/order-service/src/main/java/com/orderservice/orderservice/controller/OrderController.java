package com.orderservice.orderservice.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.orderservice.orderservice.model.Order;
import com.orderservice.orderservice.service.OrderServiceImpl;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderServiceImpl orderService;

    @Autowired
    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order createOrder(@RequestParam String userId,
                             @RequestParam String productId,
                             @RequestParam(defaultValue = "1") int quantity) {
        return orderService.createOrder(userId, productId, quantity);
    }
}
