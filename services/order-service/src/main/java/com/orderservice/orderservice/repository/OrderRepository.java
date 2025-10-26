package com.orderservice.orderservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.orderservice.orderservice.model.Order;

public interface OrderRepository extends MongoRepository<Order, String> {
}
