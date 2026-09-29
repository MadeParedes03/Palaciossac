package com.project.Palaciossac.dto;

public class CustomerResponse {
    private Long idCustomer;
    private String name;
    private String document;
    private String phone;
    private String address;

    public CustomerResponse() {
    }

    public CustomerResponse(Long idCustomer, String name, String document, String phone, String address) {
        this.idCustomer = idCustomer;
        this.name = name;
        this.document = document;
        this.phone = phone;
        this.address = address;
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
