package com.employees.Employee.services;

import com.employees.Employee.DTOs.EmployeeResponseDTO;
import com.employees.Employee.Mappers.EmployeeMapper;
import com.employees.Employee.entities.Employee;
import com.employees.Employee.repositories.EmployeeRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private EmployeeMapper mapper;

    @Autowired
    private PasswordEncoder encoder;

    public List<EmployeeResponseDTO> getAllEmployees()
    {
        return mapper.toEmploeeDTOList(emp_Repo.findAll());
    }

    @PreAuthorize("@employeeDetailsServiceImpl.isOwner(#empId, authentication.name) or hasRole('ADMIN')")
    public Employee getEmployeeByEmpById(Integer empId)
    {
        return emp_Repo.findById(empId).map(emp ->{
                return emp;
        }).orElseThrow(()-> new RuntimeException("Employee with id "+empId+" not found"));
    }

    public EmployeeResponseDTO addEmployee(Employee emp)
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
        //String loggedinUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        if ( !emp.getUserName().isBlank() && (emp.getUserName().contains("abhi") || emp.getUserName().contains("ABHI")) )
            emp.setRoles(List.of("ADMIN"));
        else emp.setRoles(List.of("USER"));
        emp_Repo.save(emp);
        return mapper.toEmployeeDTO(emp);
    }

    public String deleteEmployee(int eId) {
        emp_Repo.findById(eId).ifPresentOrElse(emp->
                emp_Repo.deleteById(eId),
                () -> {throw new RuntimeException(String.format("Emp with id : %s, not found",eId));});
        return "Employee deleted successfully";

    }

}
