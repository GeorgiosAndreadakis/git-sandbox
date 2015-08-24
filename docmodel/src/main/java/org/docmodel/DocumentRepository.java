/* Copyright 2015 by Andreadakis Consulting */
package org.docmodel;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository for the entity Document.
 *
 * @author Georgios Andreadakis (georgios@andreadakis-consulting.de)
 */
public interface DocumentRepository extends MongoRepository<Document, String> {
}