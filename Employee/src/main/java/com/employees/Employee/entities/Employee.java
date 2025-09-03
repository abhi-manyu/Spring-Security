package com.employees.Employee.entities;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

@Entity(name = "Employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer empId;
    private String empName;
    private String empCompanyName;
    private String empLocation;
    @Column(nullable = false,unique = true)
    private String userName;
    @Column(nullable = false)
    private String password;

    public Employee() {
    }

    public Employee(Integer empId, String empName, String empCompanyName, String empLocation, @NonNull String userName, @NonNull String password) {
        this.empId = empId;
        this.empName = empName;
        this.empCompanyName = empCompanyName;
        this.empLocation = empLocation;
        this.userName = userName;
        this.password = password;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpCompanyName() {
        return empCompanyName;
    }

    public void setEmpCompanyName(String empCompanyName) {
        this.empCompanyName = empCompanyName;
    }

    public String getEmpLocation() {
        return empLocation;
    }

    public void setEmpLocation(String empLocation) {
        this.empLocation = empLocation;
    }

    @NonNull
    public String getUserName() {
        return userName;
    }

    public void setUserName(@NonNull String userName) {
        this.userName = userName;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }
}
