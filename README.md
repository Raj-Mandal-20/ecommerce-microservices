# 🛒 E-commerce Microservices Backend (WIP)

A microservices-based backend for an e-commerce platform built using Spring Boot, Node.js, Kafka, gRPC, and Docker.
This project is designed as a monorepo to simulate a real-world distributed architecture where multiple services communicate via REST, Kafka events, and gRPC calls.

## 🎯 Goal

### To build a scalable, production-inspired backend that demonstrates:

✅ Synchronous REST and gRPC service communication

✅ Asynchronous messaging using Apache Kafka

✅ Containerized services via Docker Compose

✅ Cross-service reusability via shared modules and .proto contracts

✅ Progressive documentation and refactoring for real-world architecture


### 🧱 Tech Stack (Updated)
| Service                   | Language        | Framework   | Database             | Communication       | Notes                                                  |
| ------------------------- | --------------- | ----------- | -------------------- | ------------------- | ------------------------------------------------------ |
| **User Service**          | Node.js         | Express     | MongoDB              | REST + gRPC         | Manages users, authentication & profile data           |
| **Product Service**       | Node.js         | Express     | MongoDB              | REST + Kafka + gRPC | Manages product catalog and publishes stock updates    |
| **Inventory Service**     | Java            | Spring Boot | PostgreSQL           | REST + Kafka        | Manages stock levels and reacts to order events        |
| **Order Service**         | Java            | Spring Boot | PostgreSQL           | REST + Kafka + gRPC | Handles order creation, stock checks, and payment flow |
| **Payment Service**       | Java            | Spring Boot | PostgreSQL (planned) | Kafka only          | Handles payment processing asynchronously              |
| **Notification Service**  | Node.js         | Express     | —                    | Kafka only          | Sends email/SMS/notification messages                  |
| **API Gateway (planned)** | Node.js / Nginx | —           | —                    | REST                | Routes and aggregates requests between services        |
| **Kafka Broker**          | —               | —           | —                    | —                   | Event backbone for asynchronous processing             |
| **Zookeeper**             | —               | —           | —                    | —                   | Kafka cluster coordination                             |


```

ecommerce-backend/
│
├── .vscode/                      # Editor configuration
├── proto/                        # Shared gRPC service definitions (.proto files)
│   ├── order.proto
│   └── product.proto
│
├── services/
│   ├── user-service/             # Node.js (Express + MongoDB + gRPC)
│   ├── product-service/          # Node.js (Express + Kafka + MongoDB)
│   ├── inventory-service/        # Java (Spring Boot + Kafka + PostgreSQL)
│   ├── order-service/            # Java (Spring Boot + Kafka + PostgreSQL)
│   ├── payment-service/          # Java (Spring Boot)
│   └── notification-service/     # Node.js (Kafka consumer)
│
├── shared/
│   └── utils/
│       └── AppError.js           # Common utility for consistent error handling
│
├── docker-compose.yml            # Base orchestration (app + DB)
├── .env                          # Environment variables for all services
├── LICENSE
└── README.md
```


## 🔄 Communication Overview
### 🧭 REST (Synchronous) 

- User → Order → Inventory

- Product → Inventory

- Order → Payment

### ⚡ gRPC (Synchronous)

- Used for high-performance internal RPC calls between Java and Node.js services.
Shared .proto contracts stored in /proto define schemas like:

- UserService → user data lookups

- ProductService → product pricing or metadata

- OrderService → real-time order validation

### 📡 Kafka (Asynchronous)

- Used for event-driven updates:

- order-events → triggers inventory and payment updates

- inventory-events → broadcasts stock changes

- notification-events → drives user notifications
