
package com.example.demo.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityClass extends WebSecurityConfigurerAdapter
{

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.inMemoryAuthentication().
		  withUser("abhi").password("{noop}abhi@manyu").roles("user")
		  .and().
		  withUser("arabinda").password("{noop}ara@binda").roles("admin").
		  and().
		  withUser("charulata").password("{noop}charu@lata").roles("manager");
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.httpBasic().and().authorizeRequests().
		antMatchers(HttpMethod.GET,"/messages/{id}**").hasAnyRole("user","admin","manager").
		antMatchers(HttpMethod.GET,"/messages/**").hasAnyRole("admin","manager").
		antMatchers(HttpMethod.POST,"/messages/**").hasAnyRole("admin","manager").
		antMatchers(HttpMethod.DELETE,"/messages/**").hasAnyRole("manager").
		and().csrf().disable().formLogin().disable();
	}
	
}
