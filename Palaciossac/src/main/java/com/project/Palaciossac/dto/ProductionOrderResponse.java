package com.project.Palaciossac.dto;

import java.time.LocalDate;

public class ProductionOrderResponse {
    private Long idOrder;
    private Long idProduct;
    private String nanmeProduct;
    private Long idEmployee;
    private String nameEmployee;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer queantity;
    private String status;

    public ProductionOrderResponse() {
    }

    public ProductionOrderResponse(Long idOrder, Long idProduct, String nanmeProduct, Long idEmployee, LocalDate startDate, String nameEmployee, LocalDate endDate, Integer queantity, String status) {
        this.idOrder = idOrder;
        this.idProduct = idProduct;
        this.nanmeProduct = nanmeProduct;
        this.idEmployee = idEmployee;
        this.startDate = startDate;
        this.nameEmployee = nameEmployee;
        this.endDate = endDate;
        this.queantity = queantity;
        this.status = status;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getNanmeProduct() {
        return nanmeProduct;
    }

    public void setNanmeProduct(String nanmeProduct) {
        this.nanmeProduct = nanmeProduct;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
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

    public Integer getQueantity() {
        return queantity;
    }

    public void setQueantity(Integer queantity) {
        this.queantity = queantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
