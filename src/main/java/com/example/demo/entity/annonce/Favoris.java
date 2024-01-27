package com.example.demo.entity.annonce;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "favoris")
public class Favoris {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idfavoris;
    
    @Column(name = "iduser")
    int iduser;
    
    @Column(name = "idannonce")
    int idannonce;
    
    @Column(name = "dateabo")
    LocalDate dateabo;

    //constructeur, getters, setters

    public Favoris() {  
    }

    public Favoris(int iduser, int idannonce, LocalDate dateabo) {
        this.iduser = iduser;
        this.idannonce = idannonce;
        this.dateabo = dateabo;
    }
    
    public int getIdfavoris() {
        return idfavoris;
    }
    public void setIdfavoris(int idfavoris) {
        this.idfavoris = idfavoris;
    }
    public int getIduser() {
        return iduser;
    }
    public void setIduser(int iduser) {
        this.iduser = iduser;
    }
    public int getIdannonce() {
        return idannonce;
    }
    public void setIdannonce(int idannonce) {
        this.idannonce = idannonce;
    }
    public LocalDate getDateabo() {
        return dateabo;
    }
    public void setDateabo(LocalDate dateabo) {
        this.dateabo = dateabo;
    }    
}
