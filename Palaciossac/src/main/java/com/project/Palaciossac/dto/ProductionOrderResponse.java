package com.project.Palaciossac.dto;

import com.project.Palaciossac.entity.ProductionStatus;

import java.time.LocalDateTime;

public class ProductionOrderResponse {

    private Long id;

    private Long productId;

    private String productName;

    private Long employeeId;

    private String employeeName;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Integer quantity;

    private ProductionStatus status;

    public ProductionOrderResponse() {
    }

    public ProductionOrderResponse(Long id, Long productId, String productName, Long employeeId, String employeeName, LocalDateTime startDate, LocalDateTime endDate, Integer quantity, ProductionStatus status) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.quantity = quantity;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ProductionStatus getStatus() {
        return status;
    }

    public void setStatus(ProductionStatus status) {
        this.status = status;
    }
}
