package com.orderservice.orderservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.orderservice.orderservice.grpc.ProductServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Configuration
public class GrpcClientConfig {

    private final ProductGrpcProperties productGrpcProperties;

    public GrpcClientConfig(ProductGrpcProperties productGrpcProperties) {
        this.productGrpcProperties = productGrpcProperties;
    }

    @Bean
    public ManagedChannel productServiceChannel() {
        return ManagedChannelBuilder
                .forAddress(productGrpcProperties.getHost(), productGrpcProperties.getPort())
                .usePlaintext()
                .build();
    }

    @Bean
    public ProductServiceGrpc.ProductServiceBlockingStub productServiceBlockingStub(
            ManagedChannel productServiceChannel) {
        return ProductServiceGrpc.newBlockingStub(productServiceChannel);
    }
}
