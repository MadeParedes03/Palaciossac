package com.project.Palaciossac.entity;

import jakarta.persistence.*;

@Entity
@Table (name = "employee")
public class Employee {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "idEmployee")
    private Long idEmployee;

    @Column(nullable = false, length = 120)
    private String nameEmployee;

    @Column(length = 80)
    private String position;

    @Column(length = 20)
    private String phoneEmployee;

    public Employee() {
    }

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
