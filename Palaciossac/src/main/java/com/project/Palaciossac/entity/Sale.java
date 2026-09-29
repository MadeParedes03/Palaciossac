package com.project.Palaciossac.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSale")
    private Long idSale;

    @ManyToOne
    @JoinColumn(name = "idCustomer")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "idEmployee")
    private Employee employee;

    @Column(nullable = false)
    private LocalDate date;

    @Column(name = "pageType", length = 30)
    private String pageType;

    @Column(nullable = false)
    private BigDecimal total = BigDecimal.ZERO;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
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
