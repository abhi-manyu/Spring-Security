package com.employees.Employee.controllers;

import com.employees.Employee.entities.Employee;
import com.employees.Employee.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService emp_Serv;

    @GetMapping
    public ResponseEntity<?> getAllEmployees()
    {
        return ResponseEntity.ok(emp_Serv.getAllEmployees());
    }
    @GetMapping("/{empId}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Integer empId)
    {
        try{
            return ResponseEntity.ok(emp_Serv.getEmployeeByEmpById(empId));
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> addEmployee(@RequestBody Employee emp)
    {
        try {
            return ResponseEntity.ok(emp_Serv.addEmployee(emp));
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
