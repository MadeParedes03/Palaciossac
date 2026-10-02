package com.project.Palaciossac.controller;

import com.project.Palaciossac.dto.ProductRecipeRequest;
import com.project.Palaciossac.dto.ProductRecipeResponse;
import com.project.Palaciossac.dto.ProductRecipeUpdateRequest;
import com.project.Palaciossac.service.ProductRecipeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class ProductRecipeController {

    private final ProductRecipeService productRecipeService;

    public ProductRecipeController(ProductRecipeService productRecipeService) {
        this.productRecipeService = productRecipeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductRecipeResponse create(@Valid @RequestBody ProductRecipeRequest request) {
        return productRecipeService.create(request);
    }

    @GetMapping
    public List<ProductRecipeResponse> findAll() {
        return productRecipeService.findAll();
    }

    @GetMapping("/by-product/{productId}")
    public List<ProductRecipeResponse> findByProduct(@PathVariable Long productId) {
        return productRecipeService.findByProduct(productId);
    }

    @PutMapping("/{productId}/{materialId}")
    public ProductRecipeResponse updateQuantity(@PathVariable Long productId,
                                                @PathVariable Long materialId,
                                                @Valid @RequestBody ProductRecipeUpdateRequest request) {
        return productRecipeService.updateQuantity(productId, materialId, request);
    }

    @DeleteMapping("/{productId}/{materialId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long productId, @PathVariable Long materialId) {
        productRecipeService.delete(productId, materialId);
    }
}
