package com.bootexample;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.bootexample.ui.StartPage;

@Component
@EnableAutoConfiguration
@ComponentScan(basePackages = {"org.docmodel", "org.reqmodel", "com.bootexample"})
public class SpringBootGradleMultiprojectApplication extends WebApplication {

    @Autowired
    private ApplicationContext ctx;

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
        getComponentInstantiationListeners().add(new SpringComponentInjector(this, ctx));
    }
}
