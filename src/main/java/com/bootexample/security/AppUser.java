/* Copyright 2015 by Andreadakis Consulting */
package com.bootexample.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Models an user of this example application.
 *
 * @author Georgios Andreadakis (georgios@andreadakis-consulting.de)
 */
public class AppUser implements UserDetails {

    private Collection<GrantedAuthority> authorities = new ArrayList<>();
    private String password;
    private String username;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void addAuthority(GrantedAuthority authority) {
        this.authorities.add(authority);
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public AppUser(String user, String pwd) {
        this.username = user;
        this.password = pwd;
    }
}