package com.employees.Employee.securityConfig;

import com.employees.Employee.entities.Employee;
import com.employees.Employee.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class EmployeeDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private EmployeeRepository empRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee emp = Optional.ofNullable(empRepo.findByUserName(username))
                .orElseThrow(() -> new UsernameNotFoundException("user name not found : " + username));
        return User.withUsername(emp.getUserName())
                .password(emp.getPassword())
                .roles(emp.getRoles().toArray(new String[0]))
                .build();
    }

    public boolean isOwner(Integer empId, String loggedInUser) {
        return empRepo.findById(empId)
                .map(emp -> emp.getUserName().equals(loggedInUser))
                .orElse(false);

    }
}
