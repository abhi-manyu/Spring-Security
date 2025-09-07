package com.employees.Employee.services;

import com.employees.Employee.entities.Employee;
import com.employees.Employee.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository emp_Repo;
    @Autowired
    private PasswordEncoder encoder;

    public List<Employee> getAllEmployees()
    {
        return emp_Repo.findAll();
    }

    public Employee getEmployeeByEmpById(Integer empId)
    {
        return emp_Repo.findById(empId).map(emp ->{
            return emp;
        }).orElseThrow(()-> new RuntimeException("Employee with id "+empId+" not found"));
    }

    public Employee addEmployee(Employee emp)
    {
        Optional.ofNullable(emp.getUserName()).filter(uName-> !uName.isBlank())
                .orElseThrow(()-> new RuntimeException("Username cannot be null or empty"));
        Optional.ofNullable(emp.getPassword()).filter(pass->!pass.isBlank())
                .orElseThrow(()->new RuntimeException("Password cannot be null or empty"));

        Optional.ofNullable(emp_Repo.findByUserName(emp.getUserName()))
                .ifPresent(u -> {
                    throw new RuntimeException("This user is already taken");
                });
        emp.setPassword(encoder.encode(emp.getPassword()));
        emp_Repo.save(emp);
        return emp;
    }

}
