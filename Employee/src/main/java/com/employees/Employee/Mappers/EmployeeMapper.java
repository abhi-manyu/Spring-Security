package com.employees.Employee.Mappers;


import com.employees.Employee.DTOs.EmployeeRequestDTO;
import com.employees.Employee.DTOs.EmployeeResponseDTO;
import com.employees.Employee.entities.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper
{
    public EmployeeResponseDTO toEmployeeDTO(Employee emp);

    public List<EmployeeResponseDTO> toEmploeeDTOList(List<Employee> emps);

    public Employee toEmployee(EmployeeRequestDTO empDTO);
}
