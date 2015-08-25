/* Copyright 2015 by Andreadakis Consulting */
package org.docmodel.events;

import org.docmodel.Document;
import org.springframework.context.ApplicationEvent;

/**
 * An document was created.
 *
 * @author Georgios Andreadakis (georgios@andreadakis-consulting.de)
 */
public class DocumentCreatedEvent extends ApplicationEvent {

    private final Document document;
    public DocumentCreatedEvent(Object source, Document document) {
        super(source);
        this.document = document;
    }

    public Document getDocument() {
        return document;
    }
}