package com.project.Palaciossac.entity;

import java.math.BigDecimal;

public class rawMaterial {
    private Long idMaterial;
    private String name;
    private String unitsOfMeasurement;
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
