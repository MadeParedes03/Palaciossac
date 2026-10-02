package com.project.Palaciossac.dto;

import java.math.BigDecimal;

public class RawMaterialResponse {

    private Long id;

    private String name;

    private String unitOfMeasure;

    private BigDecimal stock;

    public RawMaterialResponse() {
    }

    public RawMaterialResponse(Long id, String name, String unitOfMeasure, BigDecimal stock) {
        this.id = id;
        this.name = name;
        this.unitOfMeasure = unitOfMeasure;
        this.stock = stock;
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
