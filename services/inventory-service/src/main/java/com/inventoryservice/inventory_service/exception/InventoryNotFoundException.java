package com.inventoryservice.inventory_service.exception;

public class InventoryNotFoundException extends RuntimeException {
    public InventoryNotFoundException(String productId){
        super("Inventory Not Found For Product = "+productId);
    }
}
