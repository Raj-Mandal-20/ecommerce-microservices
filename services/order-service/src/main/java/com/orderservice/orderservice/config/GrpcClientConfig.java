package com.orderservice.orderservice.config;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.orderservice.orderservice.grpc.ProductServiceGrpc;


@Configuration
public class GrpcClientConfig {

    @Bean
    public ManagedChannel productServiceChannel() {
        return ManagedChannelBuilder.forAddress("localhost", 9090) // Port of product-service
                .usePlaintext()
                .build();
    }

    @Bean
    public ProductServiceGrpc.ProductServiceBlockingStub productServiceStub(ManagedChannel productServiceChannel) {
        return ProductServiceGrpc.newBlockingStub(productServiceChannel);
    }
}
