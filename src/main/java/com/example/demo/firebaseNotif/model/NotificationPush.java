package com.example.demo.firebaseNotif.model;

public class NotificationPush {
    String titre;
    String contenu;
    String token;

    public NotificationPush(String titre, String contenu, String token) {
        this.titre = titre;
        this.contenu = contenu;
        this.token = token;
    }
    

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
