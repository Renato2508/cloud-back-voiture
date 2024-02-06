package com.example.demo.firebaseNotif.service;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.example.demo.firebaseNotif.model.NotificationPush;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

@Service
public class NotificationPushService {

    public String sendNotif(NotificationPush notifToSend)
            throws FirebaseMessagingException, InterruptedException, ExecutionException {
        Notification notification = Notification.builder().setTitle(notifToSend.getTitre())
                .setBody(notifToSend.getContenu()).build();
        Message message = Message.builder().setToken(notifToSend.getToken()).setNotification(notification).build();
        return FirebaseMessaging.getInstance().sendAsync(message).get();
    }
}
