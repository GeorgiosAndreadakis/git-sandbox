package com.bootexample.ui;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.reqmodel.RequirementMetaInfo;

import com.bootexample.HomePage;

public class StartPage extends WebPage {

    @SpringBean
    @SuppressWarnings("serial, unused")
    private RequirementMetaInfo requirementMetaInfo;

    public StartPage() {

        Label message = new Label("message", new LoadableDetachableModel<String>() {
            @Override
            protected String load() {
                return requirementMetaInfo.meinSenf() +  "\nAhoi!";
            }
        });
        add(message);

        add(new Link("linkToHome") {
            @Override
            public void onClick() {
                setResponsePage(HomePage.class);
            }
        });
    }
}
