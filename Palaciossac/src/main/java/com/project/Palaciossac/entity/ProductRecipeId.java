package com.project.Palaciossac.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductRecipeId implements Serializable {

    @Column(name = "id_producto")
    private Long productId;

    @Column(name = "id_insumo")
    private Long materialId;

    public ProductRecipeId() {
    }

    public ProductRecipeId(Long productId, Long materialId) {
        this.productId = productId;
        this.materialId = materialId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductRecipeId that)) return false;
        return Objects.equals(productId, that.productId) && Objects.equals(materialId, that.materialId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, materialId);
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }
}
