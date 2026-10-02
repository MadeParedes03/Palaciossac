package com.project.Palaciossac.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class SaleRequest {

    @NotNull(message = "El cliente es obligatorio")
    private Long customerId;

    @NotNull(message = "El empleado es obligatorio")
    private Long employeeId;

    @NotBlank(message = "El tipo de pago es obligatorio")
    @Size(max = 30, message = "El tipo de pago no puede superar 30 caracteres")
    private String paymentType;

    @NotEmpty(message = "La venta debe tener al menos un producto")
    @Valid
    private List<SaleItemRequest> items;

    public SaleRequest() {
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public List<SaleItemRequest> getItems() {
        return items;
    }

    public void setItems(List<SaleItemRequest> items) {
        this.items = items;
    }
}
