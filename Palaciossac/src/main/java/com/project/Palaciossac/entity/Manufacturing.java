package com.project.Palaciossac.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

public class Manufacturing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idManufacturing")
    private Long idManufacturing;

    @ManyToOne
    @JoinColumn(name = "idProduct")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "id_rawMaterial")
    private rawMaterial rawMaterial;

    @Column(name = "requeryQuantity", nullable = false)
    private BigDecimal requeryQuantity;

    public Manufacturing() {
    }

    public Manufacturing(Product product, rawMaterial rawMaterial, BigDecimal requeryQuantity) {
        this.product = product;
        this.rawMaterial = rawMaterial;
        this.requeryQuantity = requeryQuantity;
    }

    public Long getIdManufacturing() {
        return idManufacturing;
    }

    public void setIdManufacturing(Long idManufacturing) {
        this.idManufacturing = idManufacturing;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public rawMaterial getRawMaterial() {
        return rawMaterial;
    }

    public void setRawMaterial(rawMaterial rawMaterial) {
        this.rawMaterial = rawMaterial;
    }

    public BigDecimal getRequeryQuantity() {
        return requeryQuantity;
    }

    public void setRequeryQuantity(BigDecimal requeryQuantity) {
        this.requeryQuantity = requeryQuantity;
    }
}
