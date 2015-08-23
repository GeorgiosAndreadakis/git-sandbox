/* Copyright 2015 by Andreadakis Consulting */
package com.bootexample;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring Security configuration.
 *
 * @author Georgios Andreadakis (georgios@andreadakis-consulting.de)
 */
@Configuration
@EnableWebSecurity
@SuppressWarnings("unused")
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/logout")
                .permitAll()
                .antMatchers("/**")
                .hasRole("USER")
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll();

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("dummy")
                .password("dummy")
                .roles("USER");
    }
}