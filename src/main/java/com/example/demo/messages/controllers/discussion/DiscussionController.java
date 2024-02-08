package com.example.demo.messages.controllers.discussion;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import com.example.demo.authentication.etudiant.springWeb.entities.Utilisateur;
import com.example.demo.authentication.etudiant.springWeb.repositories.UtilisateurRepository;
import com.example.demo.authentication.etudiant.springWeb.tools.JwtUtil;
import com.example.demo.messages.annexes.RequestDestinatiare;
import com.example.demo.messages.documents.Discussion;
import com.example.demo.messages.documents.Message;
import com.example.demo.messages.repositories.MessageRepository;
import com.example.demo.messages.services.DiscussionService;
import com.example.demo.response.Response;

@RestController
@RequestMapping("/discussion")
@PreAuthorize("hasRole('USER')")
public class DiscussionController {
    @Autowired
    private DiscussionService discussionService;

    @Autowired 
    private MessageRepository messageRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private JwtUtil jwtUtil;


    @GetMapping("/messages")
    public Response getAllMessagesForDiscusion(@RequestBody Discussion discussion){
        try {
            List<Message> lm = this.discussionService.getAllMessagesForDiscussion(discussion.getIdDiscussion());
            return new Response("200", false, lm);
        } catch (Exception e) {
            return new Response("500", true, null);
        }
    }
    

    @GetMapping("/discussions")
    public Response getAllDiscussionForUser(@RequestHeader(name = "Authorization") String token){
        Utilisateur u = jwtUtil.extractUser(token);
        try {
            List<Discussion> res = discussionService.getAllDiscussionsForUser(u.getIdUser());
            return new Response("200", false, res);
        } catch (NoSuchElementException e) {
            return new Response("200", false, null);
        }
    }

    @PostMapping("/new_message")
    public ResponseEntity<?> nouvelleDiscussion(@RequestHeader(name = "Authorization") String token, @RequestBody RequestDestinatiare destinataire){
        try {
            Utilisateur sender = jwtUtil.extractUser(token);
            Utilisateur receiver = this.utilisateurRepository.findByIdUser(destinataire.getIddestinataire()).get();
            Discussion d = new Discussion(sender, receiver, destinataire.getMessage()) ;

            this.discussionService.saveDiscussion(d);
            //this.discussionService.send_notif(sender, destinataire);

            Response res = new Response("message envoyé", false, null);
            return new ResponseEntity<Response>(res, HttpStatus.OK);
        } catch (Exception e) {
            Response res = new Response(e.getMessage(), true, null);
            return new ResponseEntity<Response>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
    
    @PostMapping("/message")
    public ResponseEntity<?> send_message(@RequestHeader(name = "Authorization") String token, @RequestBody RequestDestinatiare destinataire){
        Utilisateur sender = jwtUtil.extractUser(token);
        Utilisateur receiver = this.utilisateurRepository.findByIdUser(destinataire.getIddestinataire()).get();
        try {
            List<Discussion> existant = discussionService.getDiscussionsForUsers(destinataire.getIddestinataire(),  sender.getIdUser());  
            Discussion d = existant.get(0);
            
            Message m = new Message(UUID.randomUUID().toString(), LocalDateTime.now(), sender.getIdUser(), destinataire.getMessage(), d.getIdDiscussion());

            this.messageRepository.save(m);
            //this.discussionService.send_notif(sender, destinataire);

            Response res = new Response("message envoyé", false, null);
            return new ResponseEntity<Response>(res, HttpStatus.OK);
        }
        catch(NoSuchElementException ne){
            try {
                ne.printStackTrace();
                Discussion d = new Discussion(sender, receiver, destinataire.getMessage()) ;

                this.discussionService.saveDiscussion(d);
                //this.discussionService.send_notif(sender, destinataire);
                
                Response res = new Response("message envoyé", false, null);
                return new ResponseEntity<Response>(res, HttpStatus.OK);
            } catch (Exception e) {
                Response res = new Response(e.getMessage(), true, null);
                 return new ResponseEntity<Response>(res, HttpStatus.INTERNAL_SERVER_ERROR);

            }

        }  
        catch(Exception ex)  {
            Response res = new Response(ex.getMessage(), true, null);
            return new ResponseEntity<Response>(res, HttpStatus.INTERNAL_SERVER_ERROR);

        } 
    }
}
