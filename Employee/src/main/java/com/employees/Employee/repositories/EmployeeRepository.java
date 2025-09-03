package com.employees.Employee.repositories;

import com.employees.Employee.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    public Employee findByUserName(String userName);
}
