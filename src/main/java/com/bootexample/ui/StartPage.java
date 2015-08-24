package com.bootexample.ui;

import com.bootexample.HomePage;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeAction;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.docmodel.Document;
import org.docmodel.DocumentRepository;
import org.reqmodel.RequirementMetaInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

@AuthorizeAction(action = "ENABLE", roles = {"ROLE_USER"})
public class StartPage extends WebPage {

    @SpringBean
    @SuppressWarnings("serial, unused")
    private RequirementMetaInfo requirementMetaInfo;

    @SpringBean
    @SuppressWarnings("serial, unused")
    private DocumentRepository documentRepository;

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new Label("message", new LoadableDetachableModel<String>() {
            @Override
            protected String load() {
                return requirementMetaInfo.meinSenf() + "\nAhoi!";
            }
        }));

        add(new ListView<Document>("listItems", documentRepository.findAll()) {
            @Override
            protected void populateItem(ListItem<Document> item) {
                item.add(new Label("title", new PropertyModel(item.getModel(), "title")));
            }
        });

        add(new Link<Void>("linkToHome") {
            @Override
            public void onClick() {
                setResponsePage(HomePage.class);
            }
        });

        add(new Label("userRightsLabel", buildSecurityInfo()));
    }

    private String buildSecurityInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        StringBuilder sb = new StringBuilder();
        sb.append("User '");
        sb.append(authentication.getPrincipal());
        sb.append("' with roles: ");
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            sb.append(authority.getAuthority());
        }
        return sb.toString();
    }
}
