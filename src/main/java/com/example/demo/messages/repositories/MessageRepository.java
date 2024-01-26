package com.example.demo.messages.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.messages.documents.Message;

public interface MessageRepository extends MongoRepository<Message, String> {
    public List<Message> findByIdDiscussion(String idDiscussion);
}
