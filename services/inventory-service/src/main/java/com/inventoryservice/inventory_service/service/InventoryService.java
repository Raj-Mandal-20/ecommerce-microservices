package com.inventoryservice.inventory_service.service;

import org.springframework.stereotype.Service;

import com.inventoryservice.inventory_service.exception.InsufficientStockException;
import com.inventoryservice.inventory_service.exception.InventoryNotFoundException;
import com.inventoryservice.inventory_service.model.Inventory;
import com.inventoryservice.inventory_service.repository.InventoryRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<Inventory> getAll() {
        return inventoryRepository.findAll();
    }

    public Inventory getByProductId(String productId) {
        return inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new InventoryNotFoundException(productId));
    }

    @Transactional
    public Inventory addStock(String productId, int quantity) {
        Inventory inv = inventoryRepository.findByProductId(productId)
                .orElse(Inventory.builder().productId(productId).quantity(0).build());

        inv.setQuantity(inv.getQuantity() + quantity);
        return inventoryRepository.save(inv);
    }

    @Transactional
    public Inventory reduceStock(String productId, int quantity) {
        Inventory inv = getByProductId(productId);
        if (inv.getQuantity() < quantity) throw new InsufficientStockException(productId);
        inv.setQuantity(inv.getQuantity() - quantity);
        return inventoryRepository.save(inv);
    }
}