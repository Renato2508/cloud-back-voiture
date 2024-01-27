package com.example.demo.messages.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.messages.documents.Discussion;

public interface DiscussionRepository extends MongoRepository<Discussion, String> {
    
}
