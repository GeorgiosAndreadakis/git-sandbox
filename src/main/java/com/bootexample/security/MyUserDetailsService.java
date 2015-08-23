/* Copyright 2015 by Andreadakis Consulting */
package com.bootexample.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * A custom user details service.
 *
 * @author Georgios Andreadakis (georgios@andreadakis-consulting.de)
 */
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        switch (username) {
            case "jan":
                AppUser jan = new AppUser("jan", "jan");
                setUserAuthority(jan);
                return jan;
            case "georgios":
                AppUser georgios = new AppUser("georgios", "georgios");
                setUserAuthority(georgios);
                return georgios;
            default:
                throw new UsernameNotFoundException(username);
        }
    }

    private void setUserAuthority(AppUser user) {
        user.addAuthority(new SimpleGrantedAuthority("ROLE_USER"));
    }
}