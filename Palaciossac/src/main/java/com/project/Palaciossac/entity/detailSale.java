package com.project.Palaciossac.entity;

import java.math.BigDecimal;

public class detailSale {

    private Long idDetailSale;
    private Sale sale;
    private Product product;
    private Integer amount;
    private BigDecimal unitaryPrice;
    private BigDecimal subTotal;


    public detailSale(Product product, Integer amount, BigDecimal unitaryPrice) {
        this.product = product;
        this.amount = amount;
        this.unitaryPrice = unitaryPrice;
        this.subTotal = unitaryPrice.multiply(BigDecimal.valueOf(amount));
    }

    public Long getIdDetailSale() {
        return idDetailSale;
    }

    public void setIdDetailSale(Long idDetailSale) {
        this.idDetailSale = idDetailSale;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getUnitaryPrice() {
        return unitaryPrice;
    }

    public void setUnitaryPrice(BigDecimal unitaryPrice) {
        this.unitaryPrice = unitaryPrice;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

}
