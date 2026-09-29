package com.project.Palaciossac.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Sale {

    private Long idSale;
    private Customer customer;
    private Employee employee;
    private LocalDate date;
    private String pageType;
    private BigDecimal total = BigDecimal.ZERO;
    private List<detailSale> details = new ArrayList<>();

    public Sale() {
    }

    public Sale (Customer customer, Employee employee, String pageType) {
        this.customer = customer;
        this.employee = employee;
        this.pageType = pageType;
        this.date = LocalDate.now();

    }

    public void addDetails (detailSale detail){
        detail.setSale(this);
        details.add(detail);
    }

    public Long getIdSale() {
        return idSale;
    }

    public void setIdSale(Long idSale) {
        this.idSale = idSale;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<detailSale> getDetails() {
        return details;
    }

    public void setDetails(List<detailSale> details) {
        this.details = details;
    }
}
