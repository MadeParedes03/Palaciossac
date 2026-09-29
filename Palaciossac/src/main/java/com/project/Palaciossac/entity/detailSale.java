package com.project.Palaciossac.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "detailSale")
public class detailSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDetailSale")
    private Long idDetailSale;

    @ManyToOne
    @JoinColumn(name = "idSale" )
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "idProduct")
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "unitPrice",nullable = false)
    private BigDecimal unitPrice;

    @Column(nullable = false)
    private BigDecimal subTotal;


    public detailSale(Product product, Integer quantity, BigDecimal unitPrice) {
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subTotal = unitPrice.multiply(BigDecimal.valueOf(quantity));
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

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

}
