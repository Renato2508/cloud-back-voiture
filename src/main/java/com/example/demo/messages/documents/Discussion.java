package com.example.demo.messages.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.example.demo.authentication.etudiant.springWeb.entities.Utilisateur;


@Document(collection = "discussions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Discussion {
    @Id
    @Field("idDiscussion")
    String idDiscussion;

    @Field("id1")
    int id1;

    @Field("id2")
    int id2;

    @Transient
    List<Message> messages = new ArrayList<Message>();
    
    public Discussion(Utilisateur sender, int receiver, String msg){
        String uuid = UUID.randomUUID().toString();
        this.idDiscussion = uuid;
        this.id1 = sender.getIdUser();
        this.id2 = receiver;
        this.messages.add(new Message(UUID.randomUUID().toString(), LocalDateTime.now(), sender.getIdUser(), msg, this.idDiscussion)); 
    }
}
