package com.employees.Employee.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.processing.Exclude;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "employee_roles", joinColumns = @JoinColumn(name = "emp_id"))
    @Column(name = "role")
    private List<String> roles=new ArrayList<>();

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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
