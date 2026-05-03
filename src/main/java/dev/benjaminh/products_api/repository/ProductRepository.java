package dev.benjaminh.products_api.repository;

import dev.benjaminh.products_api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {}