package com.project.Palaciossac.dto;

import java.math.BigDecimal;

public class ProductResponse {

    private Long id;

    private String name;

    private String type;

    private String size;

    private String color;

    private BigDecimal salePrice;

    private Integer stock;

    public ProductResponse() {
    }

    public ProductResponse(Long id, String name, String type, String size, String color, BigDecimal salePrice, Integer stock) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.size = size;
        this.color = color;
        this.salePrice = salePrice;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
