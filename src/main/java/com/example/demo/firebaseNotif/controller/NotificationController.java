package com.example.demo.firebaseNotif.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.firebaseNotif.model.NotificationPush;
import com.example.demo.firebaseNotif.service.NotificationPushService;
import com.google.firebase.messaging.FirebaseMessagingException;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("notif")
public class NotificationController {
    @Autowired
    NotificationPushService notifService;

    @PostMapping
    public ResponseEntity<String> sendNotif(@RequestBody NotificationPush notif) {
        String res;
        try {
            notif.setContenu(notif.getContenu() + LocalDateTime.now().toString());
            res = notifService.sendNotif(notif);
            return ResponseEntity.ok().body(res);
        } catch (FirebaseMessagingException | InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
