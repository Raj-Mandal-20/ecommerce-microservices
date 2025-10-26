FROM maven:3.9.3-eclipse-temurin-17 AS build

WORKDIR /app/services/order-service

# Copy pom.xml and sources
COPY services/order-service/pom.xml ./
COPY services/order-service/src ./src

# Copy proto folder from monorepo root
COPY proto /app/proto

# Download dependencies offline
RUN mvn dependency:go-offline -B

# Generate protobuf classes and compile Java
RUN mvn clean compile -DskipTests

# Package the jar
RUN mvn clean package -Pdocker -DskipTests

# Runtime image
FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY --from=build /app/services/order-service/target/order-service-0.0.1-SNAPSHOT.jar ./order-service.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "order-service.jar"]
