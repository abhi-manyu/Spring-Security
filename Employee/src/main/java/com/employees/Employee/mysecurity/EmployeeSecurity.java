package com.employees.Employee.mysecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.net.http.HttpRequest;

@Configuration
@EnableWebSecurity
public class EmployeeSecurity
{
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception
    {
        http.csrf(csrf->csrf.disable());
        http.authorizeHttpRequests((requests) -> ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)requests.anyRequest()).authenticated());
        //http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        return (SecurityFilterChain)http.build();
    }

    @Bean
    UserDetailsService userDetailsService()
    {
        UserDetails user1 = User.withUsername("user")
                .password(encodePassword().encode("user"))
                .roles("USER").build();
        UserDetails user2 = User.withUsername("admin")
                .password(encodePassword().encode("admin"))
                .roles("ADMIN").build();
        return  new InMemoryUserDetailsManager(user1,user2);
    }

    @Bean
    public BCryptPasswordEncoder encodePassword()
    {
        return new BCryptPasswordEncoder();
    }

}
