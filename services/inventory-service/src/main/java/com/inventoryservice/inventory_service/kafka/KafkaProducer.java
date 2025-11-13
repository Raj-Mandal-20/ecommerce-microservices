package com.inventoryservice.inventory_service.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private static final String TOPIC = "inventory-events";
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendInventoryUpdate(String message) {
        kafkaTemplate.send(TOPIC, message);
        System.out.println("✅ Sent message to Kafka: " + message);
    }
}
