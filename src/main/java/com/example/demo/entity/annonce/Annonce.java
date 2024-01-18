package com.example.demo.entity.annonce;

import java.sql.Date;

import com.example.demo.entity.user.User;
import com.example.demo.entity.voiture.Voiture;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "annonce")
public class Annonce {

    @Id
    @Column(name = "idannonce")
    int idannonce;

    @Column(name = "iduser")
    String iduser;

    @Column(name = "idvoiture")
    String idvoiture;

    @Column(name = "datepublication")
    Date publication;

    @Column(name = "etat")
    int etat;

    @Column(name = "commission")
    double commission; 
    
    @Column(name = "sommepayee")
    double sommepayee;

    @Column(name = "datepayement")
    Date payement;

    @Transient
    Voiture voiture;

    @Transient
    User user;

    // Constructeur ,getters, setters

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

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getIdvoiture() {
        return idvoiture;
    }

    public void setIdvoiture(String idvoiture) {
        this.idvoiture = idvoiture;
    }

    public Date getPublication() {
        return publication;
    }

    public void setPublication(Date publication) {
        this.publication = publication;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public double getSommepayee() {
        return sommepayee;
    }

    public void setSommepayee(double sommepayee) {
        this.sommepayee = sommepayee;
    }

    public Date getPayement() {
        return payement;
    }

    public void setPayement(Date payement) {
        this.payement = payement;
    }

    public Voiture getVoiture() {
        return voiture;
    }

    public void setVoiture(Voiture voiture) {
        this.voiture = voiture;
    }
}
