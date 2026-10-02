package com.project.Palaciossac.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "materia_prima")
public class RawMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_insumo")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 120)
    private String name;

    @Column(name = "unidad_medida", length = 20)
    private String unitOfMeasure;

    @Column(name = "stock", nullable = false, precision = 12, scale = 3)
    private BigDecimal stock = BigDecimal.ZERO;

    public RawMaterial() {
    }

    public RawMaterial(String name, String unitOfMeasure, BigDecimal stock) {
        this.name = name;
        this.unitOfMeasure = unitOfMeasure;
        this.stock = stock;
    }

    public boolean hasStock(BigDecimal amount) {
        return stock != null && stock.compareTo(amount) >= 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public BigDecimal getStock() {
        return stock;
    }

    public void setStock(BigDecimal stock) {
        this.stock = stock;
    }
}
