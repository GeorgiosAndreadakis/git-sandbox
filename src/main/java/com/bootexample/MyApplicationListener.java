/* Copyright 2015 by Andreadakis Consulting */
package com.bootexample;

import org.docmodel.events.DocumentCreatedEvent;
import org.docmodel.Document;
import org.docmodel.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Listener of application events.
 *
 * @author Georgios Andreadakis (georgios@andreadakis-consulting.de)
 */
@Component
@SuppressWarnings("unused")
public class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent>, ApplicationEventPublisherAware {

    @Autowired
    private DocumentRepository documentRepository;
    private ApplicationEventPublisher publisher;

    public void onApplicationEvent(ContextRefreshedEvent event) {
        saveIfNotExists("1", "1st dummy document");
        saveIfNotExists("2", "A 2nd dummy document - yeah baby yeah");
    }

    private void saveIfNotExists(String id, String title) {
        Document document = new Document(id, title);
        if (!documentRepository.exists(id)) {
            documentRepository.save(document);
            publisher.publishEvent(new DocumentCreatedEvent(this, document));
        }
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}