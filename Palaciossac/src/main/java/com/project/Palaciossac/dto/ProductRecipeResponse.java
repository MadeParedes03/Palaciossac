package com.project.Palaciossac.dto;

import java.math.BigDecimal;

public class ProductRecipeResponse {

    private Long productId;

    private String productName;

    private Long materialId;

    private String materialName;

    private String unitOfMeasure;

    private BigDecimal quantityRequired;

    public ProductRecipeResponse() {
    }

    public ProductRecipeResponse(Long productId, String productName, Long materialId, String materialName, String unitOfMeasure, BigDecimal quantityRequired) {
        this.productId = productId;
        this.productName = productName;
        this.materialId = materialId;
        this.materialName = materialName;
        this.unitOfMeasure = unitOfMeasure;
        this.quantityRequired = quantityRequired;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public BigDecimal getQuantityRequired() {
        return quantityRequired;
    }

    public void setQuantityRequired(BigDecimal quantityRequired) {
        this.quantityRequired = quantityRequired;
    }
}
