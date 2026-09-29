package com.project.Palaciossac.dto;

import java.math.BigDecimal;

public class rawMaterialRequest {

    private String name;
    private String unitsOfMeasurement;
    private BigDecimal stock;


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
