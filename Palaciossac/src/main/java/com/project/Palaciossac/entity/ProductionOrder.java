package com.project.Palaciossac.entity;

import java.time.LocalDate;

public class ProductionOrder {
    private Long idOrder;
    private Product product;
    private Employee employee;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer quantity;
    private String status;

    public ProductionOrder() {
    }

    public ProductionOrder(Product product, Employee employee, LocalDate startDate, Integer quantity, String status) {
        this.product = product;
        this.employee = employee;
        this.startDate = startDate;
        this.quantity = quantity;
        this.status = status;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
