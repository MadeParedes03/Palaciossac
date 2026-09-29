package com.project.Palaciossac.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table (name = "rayMaterial")
public class rawMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "idMaterial")
    private Long idMaterial;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(name = "unitsOfMeasurement", length = 20)
    private String unitsOfMeasurement;

    @Column(nullable = false)
    private BigDecimal stock = BigDecimal.ZERO;

    public rawMaterial() {
    }

    public rawMaterial(String name, String unitsOfMeasurement, BigDecimal stock) {
        this.name = name;
        this.unitsOfMeasurement = unitsOfMeasurement;
        this.stock = stock;
    }
     public boolean hasStock(BigDecimal amount){
        return stock !=null && stock.compareTo(amount) >= 0;
     }

    public Long getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Long idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitsOfMeasurement() {
        return unitsOfMeasurement;
    }

    public void setUnitsOfMeasurement(String unitsOfMeasurement) {
        this.unitsOfMeasurement = unitsOfMeasurement;
    }

    public BigDecimal getStock() {
        return stock;
    }

    public void setStock(BigDecimal stock) {
        this.stock = stock;
    }
}
