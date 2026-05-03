# Build stage
ARG REGISTRY=""

FROM ${REGISTRY}maven:3.9-eclipse-temurin-21-alpine AS build

WORKDIR /app

# Copy pom.xml first (cache optimization)
COPY pom.xml .
RUN mvn dependency:go-offline || true

# Copy source code and build
COPY src src
RUN mvn clean package -Dspring.profiles.active=build

# Production stage
FROM ${REGISTRY}eclipse-temurin:21-jre-alpine

# Security: Run as non-root user
RUN addgroup -g 1001 spring && \
  adduser -u 1001 -G spring -s /bin/sh -D spring

WORKDIR /app

# Copy jar from build stage
COPY --from=build --chown=spring:spring /app/target/products-api-0.0.1-SNAPSHOT.jar app.jar

# Switch to non-root user
USER spring:spring

EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=300s \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8080/products/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]