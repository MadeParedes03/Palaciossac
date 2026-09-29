package com.project.Palaciossac.dto;

public class EmployeeResponse {

    private Long idEmployee;
    private String name;
    private String position;
    private String phone;

    public EmployeeResponse() {
    }

    public EmployeeResponse(Long idEmployee, String name, String position, String phone) {
        this.idEmployee = idEmployee;
        this.name = name;
        this.position = position;
        this.phone = phone;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
