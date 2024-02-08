package com.example.demo.messages.services;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.example.demo.authentication.etudiant.springWeb.entities.Utilisateur;
import com.example.demo.authentication.etudiant.springWeb.repositories.UtilisateurRepository;
import com.example.demo.messages.annexes.RequestDestinatiare;
import com.example.demo.messages.documents.Discussion;
import com.example.demo.messages.documents.Message;
import com.example.demo.messages.repositories.DiscussionRepository;
import com.example.demo.messages.repositories.MessageRepository;
import com.google.api.services.storage.model.Notification;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.example.demo.firebaseNotif.model.NotificationPush;
import com.example.demo.firebaseNotif.service.NotificationPushService;

@Service
public class DiscussionService {
    @Autowired
    DiscussionRepository discussionRepository;
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    NotificationPushService notificationService;
    


    @Autowired
    private MongoTemplate mongoTemplate;

    public void send_notif(Utilisateur sender, RequestDestinatiare receiver) throws FirebaseMessagingException, InterruptedException, ExecutionException, Exception{
        //extraire le token de notif du receiver
        Utilisateur dest = utilisateurRepository.findByIdUser(receiver.getIddestinataire()).orElseThrow(() -> new Exception("Destinataire inexistant"));
        String notif_token = dest.getNotif_token();
       
        // construire le notification push
        String titre = String.format("Message reçcu de: %s %s", sender.getNom(), sender.getPrenom());
        String message = receiver.getMessage();
        message = (message.length() > 50) ? message.substring(0, 50) + "..." : message;
        NotificationPush notif = new NotificationPush(titre, message, notif_token);

        // envoyer la notification
        try {
            notificationService.sendNotif(notif);
        } catch (Exception e) {
            throw e;
        }
        
    }

    public List<Message> getAllMessagesForDiscussion(String idDiscussion) throws NoSuchElementException{
        List<Message> lm = null;
        lm = this.messageRepository.findByIdDiscussion(idDiscussion);
        if(lm.isEmpty())
            throw new NoSuchElementException("Discussion encore vide");
        return lm;


    }

    public List<Discussion> getDiscussionsForUsers(int id1, int id2)throws NoSuchElementException {
        List<Discussion> res = null;
        System.out.println("Recherche des discus de et ");
        Criteria criteria1 = Criteria.where("id1").is(id1);
        Criteria criteria2 = (Criteria.where("id2").is(id2));
        Criteria criteria5 = new Criteria().andOperator(criteria1, criteria2);

        Criteria criteria3 = Criteria.where("id1").is(id2);
        Criteria criteria4 = (Criteria.where("id2").is(id1));
        Criteria criteria6 = new Criteria().andOperator(criteria3, criteria4);
    


        Query query = new Query(new Criteria().orOperator( criteria5 , criteria6 ));
        System.out.println(query);
        //System.out.println(mongoTemplate.find(query, Discussion.class));
        res = mongoTemplate.find(query, Discussion.class);
        if(res.isEmpty())
            throw new NoSuchElementException("Discussion inexistante");
        return res;
    }

    

    public List<Discussion> getAllDiscussionsForUser(int id) throws NoSuchElementException{
        List<Discussion> res = null;

        Criteria criteria3 = Criteria.where("id1").is(id);
        Criteria criteria4 = (Criteria.where("id2").is(id)); 

        Query query = new Query(new Criteria().orOperator( criteria3 , criteria4 ));
        System.out.println(query);
        //System.out.println(mongoTemplate.find(query, Discussion.class));
        res = mongoTemplate.find(query, Discussion.class);
        if(res.isEmpty())
            throw new NoSuchElementException("Cet utilisateur n'a encore aucune discussion");
        
        List<Message> lm = null;
        for(Discussion d : res){
            lm = this.messageRepository.findByIdDiscussion(d.getIdDiscussion());
            d.setMessages(lm);
        }
        return res;

    }

    public void saveDiscussion(Discussion d){
        for(Message m : d.getMessages()){
            this.messageRepository.save(m);
        }
        this.discussionRepository.save(d);
    }

}
