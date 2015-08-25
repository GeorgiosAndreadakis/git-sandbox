package com.bootexample.ui;

import com.bootexample.MockWebSession;
import com.bootexample.SpringBootGradleMultiprojectApplication;
import com.bootexample.WicketTestApplicationContext;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.spring.test.ApplicationContextMock;
import org.apache.wicket.util.tester.WicketTester;
import org.docmodel.Document;
import org.docmodel.DocumentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.reqmodel.RequirementMetaInfo;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
@ContextConfiguration(classes = {WicketTestApplicationContext.class})
@WebAppConfiguration
@EnableAutoConfiguration
@TestExecutionListeners(listeners={ServletTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        WithSecurityContextTestExecutionListener.class})
public class StartPageTest {

    private ApplicationContextMock applicationContextMock;
    private RequirementMetaInfo metaInfo = mock(RequirementMetaInfo.class);
    private DocumentRepository documentRepository = mock(DocumentRepository.class);
    private WicketTester tester;

    @Before
    public void setUp(){

        Document doc1 = new Document("1", "document 1");
        List<Document> docList = Arrays.asList(doc1);
        when(documentRepository.findAll()).thenReturn(docList);

        applicationContextMock = new ApplicationContextMock();
        applicationContextMock.putBean(metaInfo);
        applicationContextMock.putBean(documentRepository);
        tester = new WicketTester(new SpringBootGradleMultiprojectApplication() {
            @Override
            protected void init() {
                final SpringComponentInjector injector = new SpringComponentInjector(this, applicationContextMock);
                getComponentInstantiationListeners().add(injector);
                injector.inject(this);
            }

            @Override
            public Session newSession(Request request, Response response) {
                MockWebSession mockMsoSession = new MockWebSession(request);
                mockMsoSession.setLocale(Locale.ENGLISH);
                return mockMsoSession;
            }
        });
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