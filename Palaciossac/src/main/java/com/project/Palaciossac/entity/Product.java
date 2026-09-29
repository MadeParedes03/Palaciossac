package com.project.Palaciossac.entity;

import java.math.BigDecimal;

public class Product {
    private Long idProduct;
    private String nameProduct;
    private String typeProduct;
    private String sizeProduct;
    private String colourProduct;
    private BigDecimal salePrice;
    private Integer stock = 0;


    public Product() {
    }

    public Product(String nameProduct, String typeProduct, String sizeProduct, String colourProduct, BigDecimal salePrice, Integer stock) {
        this.nameProduct = nameProduct;
        this.typeProduct = typeProduct;
        this.sizeProduct = sizeProduct;
        this.colourProduct = colourProduct;
        this.salePrice = salePrice;
        this.stock = stock;
    }

    public  boolean hasStock (Integer amount){
        return stock != null && stock >= amount;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(String typeProduct) {
        this.typeProduct = typeProduct;
    }

    public String getSizeProduct() {
        return sizeProduct;
    }

    public void setSizeProduct(String sizeProduct) {
        this.sizeProduct = sizeProduct;
    }

    public String getColourProduct() {
        return colourProduct;
    }

    public void setColourProduct(String colourProduct) {
        this.colourProduct = colourProduct;
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
