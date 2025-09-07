package com.employees.Employee.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class EmployeeSecurityConfig
{


    @Autowired
    private EmployeeDetailsServiceImpl empDetails;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//        Used for Inmemory UServalidations
//        @Bean
//        SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception
//        {
//            http.csrf(csrf->csrf.disable());
//            http.authorizeHttpRequests((requests) -> ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)requests.anyRequest()).authenticated());
//            //http.formLogin(Customizer.withDefaults());
//            http.httpBasic(Customizer.withDefaults());
//            return (SecurityFilterChain)http.build();
//        }
//
//        @Bean
//        public UserDetailsService userDetailsService()
//        {
//            UserDetails user1 = User.withUsername("user")
//                    .password(passwordEncoder().encode("user"))
//                    .roles("USER").build();
//            UserDetails user2 = User.withUsername("admin")
//                    .password(passwordEncoder().encode("admin"))
//                    .roles("ADMIN").build();
//            return  new InMemoryUserDetailsManager(user1,user2);
//        }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/employees/*").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/employees/**").hasRole("ADMIN")
                        .anyRequest().permitAll()
                )
                .httpBasic(withDefaults());
        return http.build();
    }

}
