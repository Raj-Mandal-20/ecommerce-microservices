package com.inventoryservice.inventory_service.exception;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String productId) {
        super("Not enough stock for product: " + productId);
    }
}
