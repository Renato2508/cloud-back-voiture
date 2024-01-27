package com.example.demo.entity.annonce;

import com.example.demo.entity.user.User;
import com.example.demo.entity.voiture.Voiture;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "annonce")
public class Annonce {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idannonce")
    int idannonce;

    @Column(name = "iduser")
    int iduser;

    @Column(name = "idvoiture")
    String idvoiture;

    @Column(name = "etat")
    int etat;
    
    @Transient
    Voiture voiture;

    @Transient
    User user;

    // Constructeur ,getters, setters

    public Annonce(int iduser, String idvoiture, int etat) {
        this.iduser = iduser;
        this.idvoiture = idvoiture;
        this.etat = etat;
    }

    public Annonce() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getIdannonce() {
        return idannonce;
    }

    public void setIdannonce(int idannonce) {
        this.idannonce = idannonce;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getIdvoiture() {
        return idvoiture;
    }

    public void setIdvoiture(String idvoiture) {
        this.idvoiture = idvoiture;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Voiture getVoiture() {
        return voiture;
    }

    public void setVoiture(Voiture voiture) {
        this.voiture = voiture;
    }
}
