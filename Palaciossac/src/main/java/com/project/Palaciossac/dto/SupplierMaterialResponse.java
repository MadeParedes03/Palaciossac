package com.project.Palaciossac.dto;

import java.math.BigDecimal;

public class SupplierMaterialResponse {

    private Long supplierId;

    private String supplierName;

    private Long materialId;

    private String materialName;

    private BigDecimal purchasePrice;

    public SupplierMaterialResponse() {
    }

    public SupplierMaterialResponse(Long supplierId, String supplierName, Long materialId, String materialName, BigDecimal purchasePrice) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.materialId = materialId;
        this.materialName = materialName;
        this.purchasePrice = purchasePrice;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
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

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
}
