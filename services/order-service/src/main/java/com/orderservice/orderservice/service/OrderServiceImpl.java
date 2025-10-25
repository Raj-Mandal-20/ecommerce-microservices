package com.orderservice.orderservice.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.orderservice.orderservice.grpc.GetProductRequest;
import com.orderservice.orderservice.grpc.GetProductResponse;
import com.orderservice.orderservice.grpc.ProductServiceGrpc;
import com.orderservice.orderservice.model.Order;
import com.orderservice.orderservice.repository.OrderRepository;

@Service
public class OrderServiceImpl {

    private final ProductServiceGrpc.ProductServiceBlockingStub productStub;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(ProductServiceGrpc.ProductServiceBlockingStub productStub,
                            OrderRepository orderRepository) {
        this.productStub = productStub;
        this.orderRepository = orderRepository;
    }

    public Order createOrder(String userId, String productId, int quantity) {
        // 1️⃣ Fetch product info via gRPC
        GetProductRequest request = GetProductRequest.newBuilder()
                .setProductId(productId)
                .build();

        GetProductResponse product = productStub.getProduct(request);

        // 2️⃣ Build order object
        Order order = new Order();
        order.setUserId(userId);
        order.setProductId(product.getProductId());
        order.setProductName(product.getName());
        order.setProductPrice(product.getPrice());
        order.setQuantity(quantity);
        order.setTotalAmount(product.getPrice() * quantity);

        // 3️⃣ Save to MongoDB
        orderRepository.save(order);

        System.out.println("✅ Order created successfully for product: " + product.getName());
        return order;
    }

    // Optional: keep your existing fetchProductInfo() for debugging
    public void fetchProductInfo(String productId) {
        GetProductRequest request = GetProductRequest.newBuilder()
                .setProductId(productId)
                .build();
        GetProductResponse response = productStub.getProduct(request);
        System.out.println("Fetched product name: " + response.getName());
    }
}
