package dev.benjaminh.products_api.controller;

import dev.benjaminh.products_api.config.CorsProperties;
import dev.benjaminh.products_api.entity.Product;
import dev.benjaminh.products_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository repo;

    @Autowired
    private CorsProperties corsProperties;

    @GetMapping("/products/environment-variable")
    public Map<String, Object> getEnvVariable() {
        return Map.of("allowedOriginPatterns", corsProperties.getAllowedOriginPatterns());
    }

    @PostMapping("/products")
    public Product save(@RequestBody Product product) {
        return repo.save(product);
    }

    @GetMapping("/products")
    public List<Product> getAll() {
        return repo.findAll();
    }

    @PutMapping("/products/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        product.setId(id);
        return repo.save(product);
    }

    @DeleteMapping("/products/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }

    @GetMapping("/products/health")
    public Map<String, String> health() {
        return Map.of("status", "UP", "timestamp", Instant.now().toString());
    }
}