package com.project.Palaciossac.entity;

import jakarta.persistence.*;

@Entity
@Table (name = "customer")
public class Customer {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer")
    private Long idCustomer;

    @Column(nullable = false, length = 120)
    private String name;

    @Column (length = 20, unique=true)
    private String document;

    @Column(length =  20)
    private String phone;

    @Column(length =  200)
    private String address;

    public Customer() {
    }

    public Customer(String address, String name, Long idCustomer, String document, String phone) {
        this.address = address;
        this.name = name;
        this.idCustomer = idCustomer;
        this.document = document;
        this.phone = phone;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
