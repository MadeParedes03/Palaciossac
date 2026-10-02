package com.project.Palaciossac.customer.infrastructure.adapter.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CustomerDto {

    private Long id;

    @NotBlank(message = "El nombre del cliente es obligatorio")
    @Size(max = 120, message = "El nombre no puede superar 120 caracteres")
    private String name;

    @Size(max = 20, message = "El documento no puede superar 20 caracteres")
    private String document;

    @Size(max = 20, message = "El teléfono no puede superar 20 caracteres")
    private String phone;

    @Size(max = 200, message = "La dirección no puede superar 200 caracteres")
    private String address;

    public CustomerDto() {
    }

    public CustomerDto(Long id, String name, String document, String phone, String address) {
        this.id = id;
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
