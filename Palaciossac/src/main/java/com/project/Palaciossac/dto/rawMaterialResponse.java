package com.project.Palaciossac.dto;

import java.math.BigDecimal;

public class rawMaterialResponse {

    private Long idMaterial;
    private String nombre;
    private String unitsOfMeasurement;
    private BigDecimal stock;


    public rawMaterialResponse() {
    }

    public rawMaterialResponse(Long idMaterial, String nombre, String unitsOfMeasurement, BigDecimal stock) {
        this.idMaterial = idMaterial;
        this.nombre = nombre;
        this.unitsOfMeasurement = unitsOfMeasurement;
        this.stock = stock;
    }

    public Long getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Long idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
