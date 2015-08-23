package com.bootexample;

import com.bootexample.ui.StartPage;
import com.bootexample.ui.security.LoginPage;
import org.apache.wicket.Page;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AnnotationsRoleAuthorizationStrategy;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@EnableAutoConfiguration
@ComponentScan(basePackages = {"org.docmodel", "org.reqmodel", "com.bootexample"})
public class SpringBootGradleMultiprojectApplication extends AuthenticatedWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(new Class[]{SpringBootGradleMultiprojectApplication.class, WebInitializer.class}, args);
    }



    @Override
    public Class<? extends Page> getHomePage() {
        return StartPage.class;
    }


    @Override
    protected void init() {
        super.init();
        getComponentInstantiationListeners().add(new SpringComponentInjector(this));
        getSecuritySettings().setAuthorizationStrategy(new AnnotationsRoleAuthorizationStrategy(this));
        mountPage("/login", LoginPage.class);
        mountPage("/home", HomePage.class);
        mountPage("/start", StartPage.class);
    }

    @Override
    protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
        return UserAuthenticatedWebSession.class;
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        return LoginPage.class;
    }
}
