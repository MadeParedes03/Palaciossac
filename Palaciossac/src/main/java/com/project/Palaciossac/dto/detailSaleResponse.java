package com.project.Palaciossac.dto;

import java.math.BigDecimal;

public class detailSaleResponse {
    private Long idDetailSale;
    private Long idProduct;
    private String nameProduct;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal subtotal;


    public detailSaleResponse() {
    }

    public detailSaleResponse(Long idDetailSale, Long idProduct, String nameProduct, Integer quantity, BigDecimal unitPrice, BigDecimal subtotal) {
        this.idDetailSale = idDetailSale;
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotal = subtotal;
    }

    public Long getIdDetailSale() {
        return idDetailSale;
    }

    public void setIdDetailSale(Long idDetailSale) {
        this.idDetailSale = idDetailSale;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

}
