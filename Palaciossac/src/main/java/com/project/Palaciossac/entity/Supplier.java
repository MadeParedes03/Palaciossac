package com.project.Palaciossac.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "proveedor")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 120)
    private String name;

    @Column(name = "telefono", length = 20)
    private String phone;

    @Column(name = "email", length = 120)
    private String email;

    @Column(name = "direccion", length = 200)
    private String address;

    public Supplier() {
    }

    public Supplier(String name, String phone, String email, String address) {
        this.name = name;
        this.phone = phone;
        this.email = email;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
