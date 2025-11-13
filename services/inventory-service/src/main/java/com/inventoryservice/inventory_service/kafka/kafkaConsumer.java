package com.inventoryservice.inventory_service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer{
    @KafkaListener(topics = "inventory-events", groupId = "inventory-service-group")
    public void consume(String message) {
        System.out.println("📩 Received message from Kafka: " + message);
        // Optionally, handle updates here (e.g., sync from order-service)
    }
}