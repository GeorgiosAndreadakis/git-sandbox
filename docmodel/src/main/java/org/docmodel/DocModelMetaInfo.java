package org.docmodel;

import org.docmodel.events.DocumentCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class DocModelMetaInfo {

    public String sayHello() {
        return  "The fabulous Doc Model is here!";
    }

    @EventListener
    public void processDocumentCreated(DocumentCreatedEvent createdEvent) {
        System.err.println("Document '" + createdEvent.getDocument().getId() + "' by [" + createdEvent.getSource() + "]");
    }
}
