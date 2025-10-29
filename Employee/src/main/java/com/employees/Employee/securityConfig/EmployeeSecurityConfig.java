package com.employees.Employee.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity
public class EmployeeSecurityConfig
{
    @Autowired
    private EmployeeDetailsServiceImpl empDetails;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        //.requestMatchers(HttpMethod.DELETE,"/employees/*").hasAnyRole("ADMIN")
                        // the above ant matcher is doing the same thing - "if role is admin and method is delete" then only this
                        // will allow, but the same thing can be replaced by using
                        // @PreAuthorize("hasAnyRole('ADMIN')") in controller or service class method.
                        //or we can use both of them together to enforce the double security.
                        .requestMatchers("/employees/*").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/employees/**").hasAnyRole("ADMIN")
                        .anyRequest().permitAll()
                )
                .httpBasic(withDefaults());
        return http.build();
    }

}
