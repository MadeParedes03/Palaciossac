package com.project.Palaciossac.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table (name = "MaterialSupplier")
public class MaterialSupplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMaterialSuplier")
    private Long idMaterialSuplier;

    @ManyToOne
    @JoinColumn(name = "idSupplier")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "idMaterial ")
    private rawMaterial rawMaterial;

    @Column(name ="purchasePrice", nullable = false)
    private BigDecimal purchasePrice;

    public MaterialSupplier() {
    }

    public MaterialSupplier(Supplier supplier, rawMaterial rawMaterial, BigDecimal purchasePrice) {
        this.supplier = supplier;
        this.rawMaterial = rawMaterial;
        this.purchasePrice = purchasePrice;
    }

    public Long getIdMaterialSuplier() {
        return idMaterialSuplier;
    }

    public void setIdMaterialSuplier(Long idMaterialSuplier) {
        this.idMaterialSuplier = idMaterialSuplier;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public rawMaterial getRawMaterial() {
        return rawMaterial;
    }

    public void setRawMaterial(rawMaterial rawMaterial) {
        this.rawMaterial = rawMaterial;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
