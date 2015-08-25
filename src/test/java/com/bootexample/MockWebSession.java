/* Copyright 2015 by Andreadakis Consulting */
package com.bootexample;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

/**
 * Mock for a web session.
 *
 * @author Georgios Andreadakis (georgios@andreadakis-consulting.de)
 */
public class MockWebSession extends AuthenticatedWebSession {

    public MockWebSession(Request request) {
        super(request);
    }

    @Override
    protected boolean authenticate(String username, String password) {
        return false;
    }

    @Override
    public Roles getRoles() {
        return null;
    }
}