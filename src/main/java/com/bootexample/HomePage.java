package com.bootexample;

import com.bootexample.ui.StartPage;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeAction;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.reqmodel.RequirementMetaInfo;

@AuthorizeAction(action = "ENABLE", roles = {"ROLE_USER"})
public class HomePage extends WebPage {

    @SpringBean
    @SuppressWarnings("serial, unused")
    private RequirementMetaInfo requirementMetaInfo;

    public HomePage() {

        Label message = new Label("message", new LoadableDetachableModel<String>() {
            @Override
            protected String load() {
                return "\nHomePage:\n" + requirementMetaInfo.meinSenf() +  "\nAhoi!";
            }
        });
        add(message);

        add(new Link("toStartPage") {
            @Override
            public void onClick() {
                setResponsePage(StartPage.class);
            }
        });
    }
}
