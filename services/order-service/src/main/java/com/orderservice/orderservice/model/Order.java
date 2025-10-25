package com.orderservice.orderservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    private String userId;
    private String productId;
    private String productName;
    private double productPrice;
    private int quantity;
    private double totalAmount;

    public void setUserId(String userId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setProductName(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setQuantity(int quantity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setTotalAmount(double d) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setProductPrice(double price) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setProductId(String productId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
