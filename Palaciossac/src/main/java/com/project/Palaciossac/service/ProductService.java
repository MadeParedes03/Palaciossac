package com.project.Palaciossac.service;

import com.project.Palaciossac.dto.ProductRequest;
import com.project.Palaciossac.dto.ProductResponse;
import com.project.Palaciossac.entity.Product;
import com.project.Palaciossac.exception.ResourceNotFoundException;
import com.project.Palaciossac.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductResponse create(ProductRequest request) {
        Product product = new Product(request.getName(), request.getType(), request.getSize(),
                request.getColor(), request.getSalePrice(), request.getStock());
        return toResponse(productRepository.save(product));
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public ProductResponse findById(Long id) {
        return toResponse(getEntity(id));
    }

    @Transactional
    public ProductResponse update(Long id, ProductRequest request) {
        Product product = getEntity(id);
        product.setName(request.getName());
        product.setType(request.getType());
        product.setSize(request.getSize());
        product.setColor(request.getColor());
        product.setSalePrice(request.getSalePrice());
        product.setStock(request.getStock());
        return toResponse(productRepository.save(product));
    }

    @Transactional
    public void delete(Long id) {
        productRepository.delete(getEntity(id));
    }

    public List<ProductResponse> search(String name, BigDecimal minPrice, Integer minStock) {
        if (name != null) {
            return productRepository.findByNameContainingIgnoreCase(name).stream()
                    .map(this::toResponse)
                    .toList();
        }
        if (minPrice != null && minStock != null) {
            return productRepository.findBySalePriceGreaterThanAndStockGreaterThan(minPrice, minStock).stream()
                    .map(this::toResponse)
                    .toList();
        }
        return findAll();
    }

    public List<ProductResponse> findAvailable() {
        return productRepository.findAvailableProducts().stream()
                .map(this::toResponse)
                .toList();
    }

    public List<ProductResponse> findByType(String type) {
        return productRepository.findByType(type).stream()
                .map(this::toResponse)
                .toList();
    }

    private Product getEntity(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado: " + id));
    }

    private ProductResponse toResponse(Product p) {
        return new ProductResponse(p.getId(), p.getName(), p.getType(), p.getSize(),
                p.getColor(), p.getSalePrice(), p.getStock());
    }
}
