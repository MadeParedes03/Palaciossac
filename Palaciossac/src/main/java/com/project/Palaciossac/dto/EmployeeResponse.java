package com.project.Palaciossac.dto;

public class EmployeeResponse {

    private Long id;

    private String name;

    private String position;

    private String phone;

    public EmployeeResponse() {
    }

    public EmployeeResponse(Long id, String name, String position, String phone) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.phone = phone;
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
