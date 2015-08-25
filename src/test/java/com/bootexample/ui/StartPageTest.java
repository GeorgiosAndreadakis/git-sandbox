package com.bootexample.ui;

import com.bootexample.SpringBootGradleMultiprojectApplication;
import com.bootexample.WebInitializer;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.util.tester.WicketTester;
import org.docmodel.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.Assert.assertFalse;


/**
 * Unit test of StartPage.
 * <p>
 *     Attention:<br/>
 *     the web application and the database must be running to pass the tests.
 * </p>
 *
 * @author Georgios Andreadakis (georgios@andreadakis-consulting.de)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringBootGradleMultiprojectApplication.class, WebInitializer.class})
@WebAppConfiguration
@EnableAutoConfiguration
@TestExecutionListeners(listeners={ServletTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        WithSecurityContextTestExecutionListener.class})
public class StartPageTest {

    private WicketTester tester;

    @Autowired
    private ApplicationContext ctx;

    @Autowired
    private SpringBootGradleMultiprojectApplication myWebApplication;

    @Before
    public void setUp(){
        tester = new WicketTester(myWebApplication, ((WebApplicationContext)ctx).getServletContext());
    }

    @Test
    @WithMockUser
    public void list_Of_Documents_is_not_empty() {
        String docListId = "listItems";
        tester.startPage(StartPage.class);
        tester.assertVisible(docListId);
        ListView<Document> listView = (ListView<Document>)tester.getLastRenderedPage().get(docListId);
        List<Document> documentList = (List<Document>) listView.getDefaultModelObject();
        assertFalse(documentList.isEmpty());
    }
}