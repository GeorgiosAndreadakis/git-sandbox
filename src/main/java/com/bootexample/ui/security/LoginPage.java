/* Copyright 2015 by Andreadakis Consulting */
package com.bootexample.ui.security;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.request.cycle.RequestCycle;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * A login page for user authentication.
 *
 * @author Georgios Andreadakis (georgios@andreadakis-consulting.de)
 */
public class LoginPage extends WebPage {

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new Label("feedback", new LoadableDetachableModel<String>() {
            @Override
            protected String load() {
                AuthenticationException exception = (AuthenticationException) ((HttpServletRequest)RequestCycle.get().getRequest().getContainerRequest()).getSession().getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
                if (exception != null) {
                    return exception.getMessage();
                }
                return "";
            }
        }));
    }
}