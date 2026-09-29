package com.project.Palaciossac.dto;

import java.math.BigDecimal;

public class ProductResponse {
    private Long idProduct;
    private String name;
    private String type;
    private String size;
    private String colour;
    private BigDecimal salePrice;
    private Integer stock;

    public ProductResponse() {
    }

    public ProductResponse(Long idProduct, String name, String type, String size, String colour, BigDecimal salePrice, Integer stock) {
        this.idProduct = idProduct;
        this.name = name;
        this.type = type;
        this.size = size;
        this.colour = colour;
        this.salePrice = salePrice;
        this.stock = stock;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
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

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
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
