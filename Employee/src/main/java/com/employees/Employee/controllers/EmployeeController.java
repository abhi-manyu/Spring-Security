package com.employees.Employee.controllers;

import com.employees.Employee.entities.Employee;
import com.employees.Employee.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

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
        catch (AccessDeniedException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
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

    @DeleteMapping("/{eId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> deleteEmployee(@PathVariable int eId)
    {
        try {
            return ResponseEntity.ok(emp_Serv.deleteEmployee(eId));
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}
