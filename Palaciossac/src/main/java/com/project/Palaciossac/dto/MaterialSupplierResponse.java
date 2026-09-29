package com.project.Palaciossac.dto;

import java.math.BigDecimal;

public class MaterialSupplierResponse {
    private Long idMaterialSupplier;
    private Long idSupplier;
    private String nameSupplier;
    private Long idMaterial;
    private String nameMaterial;
    private BigDecimal purchasePrice;

    public MaterialSupplierResponse() {
    }

    public MaterialSupplierResponse(Long idMaterialSupplier, Long idSupplier, String nameSupplier, Long idMaterial, String nameMaterial, BigDecimal purchasePrice) {
        this.idMaterialSupplier = idMaterialSupplier;
        this.idSupplier = idSupplier;
        this.nameSupplier = nameSupplier;
        this.idMaterial = idMaterial;
        this.nameMaterial = nameMaterial;
        this.purchasePrice = purchasePrice;
    }

    public Long getIdMaterialSupplier() {
        return idMaterialSupplier;
    }

    public void setIdMaterialSupplier(Long idMaterialSupplier) {
        this.idMaterialSupplier = idMaterialSupplier;
    }

    public Long getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(Long idSupplier) {
        this.idSupplier = idSupplier;
    }

    public String getNameSupplier() {
        return nameSupplier;
    }

    public void setNameSupplier(String nameSupplier) {
        this.nameSupplier = nameSupplier;
    }

    public Long getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Long idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getNameMaterial() {
        return nameMaterial;
    }

    public void setNameMaterial(String nameMaterial) {
        this.nameMaterial = nameMaterial;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
}
