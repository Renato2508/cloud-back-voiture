package com.example.demo.messages.documents;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "messages")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Message {
    @Id
    @Field("idMessage")
    String idmessage;
    @Field("envoi")
    LocalDateTime envoi;
    @Field("expediteur")
    int expediteur;
    @Field("message")
    String message;
    @Field("idDiscussion")
    String idDiscussion;
    
    
}
