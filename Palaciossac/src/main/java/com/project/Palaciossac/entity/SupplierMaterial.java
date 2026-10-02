package com.project.Palaciossac.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "proveedor_insumo")
public class SupplierMaterial {

    @EmbeddedId
    private SupplierMaterialId id = new SupplierMaterialId();

    @ManyToOne
    @MapsId("supplierId")
    @JoinColumn(name = "id_proveedor")
    private Supplier supplier;

    @ManyToOne
    @MapsId("materialId")
    @JoinColumn(name = "id_insumo")
    private RawMaterial rawMaterial;

    @Column(name = "precio_compra", nullable = false, precision = 10, scale = 2)
    private BigDecimal purchasePrice;

    public SupplierMaterial() {
    }

    public SupplierMaterial(Supplier supplier, RawMaterial rawMaterial, BigDecimal purchasePrice) {
        this.id = new SupplierMaterialId(supplier.getId(), rawMaterial.getId());
        this.supplier = supplier;
        this.rawMaterial = rawMaterial;
        this.purchasePrice = purchasePrice;
    }

    public SupplierMaterialId getId() {
        return id;
    }

    public void setId(SupplierMaterialId id) {
        this.id = id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public RawMaterial getRawMaterial() {
        return rawMaterial;
    }

    public void setRawMaterial(RawMaterial rawMaterial) {
        this.rawMaterial = rawMaterial;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
}
