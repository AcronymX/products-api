package dev.benjaminh.products_api.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;

@RestController
public class ProductController {

    // Environment variable test
    @Value("${SPRING_APP_CORS_ALLOWED_ORIGIN_PATTERNS:DefaultValue}")
    private String myEnvVar;

    @GetMapping("/products/environment-variable")
    public Map<String, String> getEnvVariable() {
        return Map.of("environmentVariable", myEnvVar);
    }

    @GetMapping("/products/health")
    public Map<String, String> health() {
        return Map.of("status", "UP", "timestamp", Instant.now().toString());
    }
}