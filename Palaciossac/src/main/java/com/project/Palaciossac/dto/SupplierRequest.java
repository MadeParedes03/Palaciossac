package com.project.Palaciossac.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SupplierRequest {

    @NotBlank(message = "El nombre del proveedor es obligatorio")
    @Size(max = 120, message = "El nombre no puede superar 120 caracteres")
    private String name;

    @Size(max = 20, message = "El teléfono no puede superar 20 caracteres")
    private String phone;

    @Email(message = "El email no tiene formato válido")
    @Size(max = 120, message = "El email no puede superar 120 caracteres")
    private String email;

    @Size(max = 200, message = "La dirección no puede superar 200 caracteres")
    private String address;

    public SupplierRequest() {
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
