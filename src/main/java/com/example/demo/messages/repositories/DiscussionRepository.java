package com.example.demo.messages.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.messages.documents.Discussion;

import java.util.List;
import java.util.Optional;

public interface DiscussionRepository extends MongoRepository<Discussion, String> {
    
}
