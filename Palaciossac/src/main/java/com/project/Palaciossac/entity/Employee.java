package com.project.Palaciossac.entity;

public class Employee {
    private Long idEmployee;
    private String nameEmployee;
    private String position;
    private String phoneEmployee;

    public Employee(String nameEmployee, String position, String phoneEmployee) {
        this.nameEmployee = nameEmployee;
        this.position = position;
        this.phoneEmployee = phoneEmployee;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhoneEmployee() {
        return phoneEmployee;
    }

    public void setPhoneEmployee(String phoneEmployee) {
        this.phoneEmployee = phoneEmployee;
    }
}
