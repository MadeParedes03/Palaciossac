package com.project.Palaciossac.customer.infrastructure.entities;

import jakarta.persistence.*;

@Entity(name = "Customer")
@Table(name = "cliente")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 120)
    private String name;

    @Column(name = "documento", length = 20, unique = true)
    private String document;

    @Column(name = "telefono", length = 20)
    private String phone;

    @Column(name = "direccion", length = 200)
    private String address;

    public CustomerEntity() {
    }

    public CustomerEntity(String name, String document, String phone, String address) {
        this.name = name;
        this.document = document;
        this.phone = phone;
        this.address = address;
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
