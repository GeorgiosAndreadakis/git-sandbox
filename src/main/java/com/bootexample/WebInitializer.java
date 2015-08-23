/* Copyright 2015 by Andreadakis Consulting */
package com.bootexample;

import org.apache.wicket.protocol.http.WicketFilter;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.DispatcherType;
import javax.servlet.FilterConfig;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.EnumSet;

/**
 * This class is the replacement of the web.xml.
 * It registers the wicket filter in the spring aware configuration style.
 *
 * @author Georgios Andreadakis
 */
@Configuration
public class WebInitializer implements ServletContextInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        // Spring Securityn Filter
        FilterRegistration.Dynamic springSecurityFilterChainReg = servletContext.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class);
        springSecurityFilterChainReg.addMappingForUrlPatterns(EnumSet.of(DispatcherType.ERROR, DispatcherType.REQUEST), false, "/*");

        // Wicket Filter
        WicketFilter wicketFilter = new WicketFilter(new SpringBootGradleMultiprojectApplication()) {
            @Override
            public void init(boolean isServlet, FilterConfig filterConfig) throws ServletException {
                setFilterPath("");
                super.init(isServlet, filterConfig);
            }
        };
        FilterRegistration.Dynamic wicketFilterReg = servletContext.addFilter("wicketFilter", wicketFilter);
        wicketFilterReg.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "*");
    }
}