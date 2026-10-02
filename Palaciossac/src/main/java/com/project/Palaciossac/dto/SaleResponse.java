package com.project.Palaciossac.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class SaleResponse {

    private Long id;

    private LocalDateTime saleDate;

    private String customerName;

    private String employeeName;

    private String paymentType;

    private List<SaleDetailResponse> items;

    private BigDecimal total;

    public SaleResponse() {
    }

    public SaleResponse(Long id, LocalDateTime saleDate, String customerName, String employeeName, String paymentType, List<SaleDetailResponse> items, BigDecimal total) {
        this.id = id;
        this.saleDate = saleDate;
        this.customerName = customerName;
        this.employeeName = employeeName;
        this.paymentType = paymentType;
        this.items = items;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public List<SaleDetailResponse> getItems() {
        return items;
    }

    public void setItems(List<SaleDetailResponse> items) {
        this.items = items;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
