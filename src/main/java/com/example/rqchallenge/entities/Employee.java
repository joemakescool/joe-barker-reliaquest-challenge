package com.example.rqchallenge.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {

    @JsonProperty("id")
    Integer id;

    @JsonProperty("employee_name")
    String employeeName;

    @JsonProperty("employee_salary")
    Integer employeeSalary;

    @JsonProperty("employee_age")
    Integer employeeAge;

    @JsonProperty("profile_image")
    String profileImage;


    //-- Getters --//
    public Integer getId() {
        return id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public Integer getEmployeeAge() {
        return employeeAge;
    }

    public Integer getEmployeeSalary() {
        return employeeSalary;
    }

    public String getProfileImage() {
        return profileImage;
    }

    //-- Setters --//
    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setEmployeeAge(Integer employeeAge) {
        this.employeeAge = employeeAge;
    }

    public void setEmployeeSalary(Integer employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
