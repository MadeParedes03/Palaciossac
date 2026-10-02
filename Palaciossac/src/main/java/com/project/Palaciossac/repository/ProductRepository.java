package com.project.Palaciossac.repository;

import com.project.Palaciossac.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findBySalePriceGreaterThanAndStockGreaterThan(BigDecimal price, Integer stock);

    List<Product> findByType(String type);

    @Query("SELECT p FROM Product p WHERE p.stock > 0")
    List<Product> findAvailableProducts();
}
