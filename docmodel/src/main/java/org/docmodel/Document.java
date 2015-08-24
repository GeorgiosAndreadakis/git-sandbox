/* Copyright 2015 by Andreadakis Consulting */
package org.docmodel;

import org.springframework.data.annotation.Id;

/**
 * Test entity
 *
 * @author Georgios Andreadakis (georgios@andreadakis-consulting.de)
 */
public class Document {

    @Id
    private String id;
    private String title;

    public Document(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}