package com.inventoryservice.inventory_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventoryservice.inventory_service.model.Inventory;
import com.inventoryservice.inventory_service.service.InventoryService;

@RestController
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/all")
    public List<Inventory> getAll() {
        return inventoryService.getAll();
    }

    @GetMapping("/{productId}")
    public Inventory getByProduct(@PathVariable String productId) {
        return inventoryService.getByProductId(productId);
    }

    @PostMapping("/{productId}/add")
    public Inventory addStock(@PathVariable String productId, @RequestParam int qty) {
        return inventoryService.addStock(productId, qty);
    }

    @PostMapping("/{productId}/reduce")
    public Inventory reduceStock(@PathVariable String productId, @RequestParam int qty) {
        return inventoryService.reduceStock(productId, qty);
    }
}