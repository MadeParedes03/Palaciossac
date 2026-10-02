package com.project.Palaciossac.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "receta_confeccion")
public class ProductRecipe {

    @EmbeddedId
    private ProductRecipeId id = new ProductRecipeId();

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "id_producto")
    private Product product;

    @ManyToOne
    @MapsId("materialId")
    @JoinColumn(name = "id_insumo")
    private RawMaterial rawMaterial;

    @Column(name = "cantidad_requerida", nullable = false, precision = 12, scale = 3)
    private BigDecimal quantityRequired;

    public ProductRecipe() {
    }

    public ProductRecipe(Product product, RawMaterial rawMaterial, BigDecimal quantityRequired) {
        this.id = new ProductRecipeId(product.getId(), rawMaterial.getId());
        this.product = product;
        this.rawMaterial = rawMaterial;
        this.quantityRequired = quantityRequired;
    }

    public ProductRecipeId getId() {
        return id;
    }

    public void setId(ProductRecipeId id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public RawMaterial getRawMaterial() {
        return rawMaterial;
    }

    public void setRawMaterial(RawMaterial rawMaterial) {
        this.rawMaterial = rawMaterial;
    }

    public BigDecimal getQuantityRequired() {
        return quantityRequired;
    }

    public void setQuantityRequired(BigDecimal quantityRequired) {
        this.quantityRequired = quantityRequired;
    }
}
