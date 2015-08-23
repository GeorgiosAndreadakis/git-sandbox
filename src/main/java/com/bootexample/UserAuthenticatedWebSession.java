/* Copyright 2015 by Andreadakis Consulting */
package com.bootexample;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Session class with authentication support based on Spring Security.
 *
 * @author Georgios Andreadakis (georgios@andreadakis-consulting.de)
 */
public class UserAuthenticatedWebSession extends AuthenticatedWebSession {

    public UserAuthenticatedWebSession(Request request) {
        super(request);
    }

    @Override
    protected boolean authenticate(String username, String password) {
        throw new UnsupportedOperationException("You are supposed to use Spring-Security!!");
    }

    @Override
    public Roles getRoles() {
        Roles roles = new Roles();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            roles.add(authority.getAuthority());
        }
        return roles;
    }
}