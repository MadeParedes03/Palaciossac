package com.project.Palaciossac.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "producto")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 120)
    private String name;

    @Column(name = "tipo", length = 60)
    private String type;

    @Column(name = "tamano", length = 30)
    private String size;

    @Column(name = "color", length = 30)
    private String color;

    @Column(name = "precio_venta", nullable = false, precision = 10, scale = 2)
    private BigDecimal salePrice;

    @Column(name = "stock", nullable = false)
    private Integer stock = 0;

    public Product() {
    }

    public Product(String name, String type, String size, String color, BigDecimal salePrice, Integer stock) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.color = color;
        this.salePrice = salePrice;
        this.stock = stock;
    }

    public boolean hasStock(Integer amount) {
        return stock != null && stock >= amount;
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
