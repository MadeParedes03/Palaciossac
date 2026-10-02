package com.project.Palaciossac.service;

import com.project.Palaciossac.dto.ProductRecipeRequest;
import com.project.Palaciossac.dto.ProductRecipeResponse;
import com.project.Palaciossac.dto.ProductRecipeUpdateRequest;
import com.project.Palaciossac.entity.Product;
import com.project.Palaciossac.entity.ProductRecipe;
import com.project.Palaciossac.entity.ProductRecipeId;
import com.project.Palaciossac.entity.RawMaterial;
import com.project.Palaciossac.exception.ResourceNotFoundException;
import com.project.Palaciossac.repository.ProductRecipeRepository;
import com.project.Palaciossac.repository.ProductRepository;
import com.project.Palaciossac.repository.RawMaterialRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductRecipeService {

    private final ProductRecipeRepository recipeRepository;
    private final ProductRepository productRepository;
    private final RawMaterialRepository rawMaterialRepository;

    public ProductRecipeService(ProductRecipeRepository recipeRepository,
                                ProductRepository productRepository,
                                RawMaterialRepository rawMaterialRepository) {
        this.recipeRepository = recipeRepository;
        this.productRepository = productRepository;
        this.rawMaterialRepository = rawMaterialRepository;
    }

    @Transactional
    public ProductRecipeResponse create(ProductRecipeRequest request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado: " + request.getProductId()));
        RawMaterial material = rawMaterialRepository.findById(request.getMaterialId())
                .orElseThrow(() -> new ResourceNotFoundException("Insumo no encontrado: " + request.getMaterialId()));

        ProductRecipeId id = new ProductRecipeId(product.getId(), material.getId());
        if (recipeRepository.existsById(id)) {
            throw new IllegalArgumentException("La receta de '" + product.getName()
                    + "' ya incluye el insumo '" + material.getName() + "'");
        }

        ProductRecipe line = new ProductRecipe(product, material, request.getQuantityRequired());
        return toResponse(recipeRepository.save(line));
    }

    public List<ProductRecipeResponse> findAll() {
        return recipeRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public List<ProductRecipeResponse> findByProduct(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException("Producto no encontrado: " + productId);
        }
        return recipeRepository.findByProductId(productId).stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public ProductRecipeResponse updateQuantity(Long productId, Long materialId,
                                                ProductRecipeUpdateRequest request) {
        ProductRecipe line = getEntity(productId, materialId);
        line.setQuantityRequired(request.getQuantityRequired());
        return toResponse(recipeRepository.save(line));
    }

    @Transactional
    public void delete(Long productId, Long materialId) {
        recipeRepository.delete(getEntity(productId, materialId));
    }

    private ProductRecipe getEntity(Long productId, Long materialId) {
        return recipeRepository.findById(new ProductRecipeId(productId, materialId))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No existe la receta producto " + productId + " - insumo " + materialId));
    }

    private ProductRecipeResponse toResponse(ProductRecipe r) {
        return new ProductRecipeResponse(
                r.getProduct().getId(), r.getProduct().getName(),
                r.getRawMaterial().getId(), r.getRawMaterial().getName(),
                r.getRawMaterial().getUnitOfMeasure(), r.getQuantityRequired());
    }
}
