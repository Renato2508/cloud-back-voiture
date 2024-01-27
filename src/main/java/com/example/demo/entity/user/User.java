package com.example.demo.entity.user;

public class User {
    int iduser;
    String email;
    String nom;
    String prenom;

    public User(int iduser, String email, String nom, String prenom) {
        this.iduser = iduser;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
    }
    
    public int getIduser() {
        return iduser;
    }
    public void setIduser(int iduser) {
        this.iduser = iduser;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}


