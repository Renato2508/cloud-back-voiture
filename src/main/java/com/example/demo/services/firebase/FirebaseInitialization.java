package com.example.demo.services.firebase;

import java.io.FileInputStream;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import jakarta.annotation.PostConstruct;

@Service
public class FirebaseInitialization {

    @PostConstruct
    public void initialization() {
        try {
            FileInputStream serviceAccount = new FileInputStream("./serviceAccountKey.json");
            FirebaseOptions options = FirebaseOptions.builder().setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();
            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

