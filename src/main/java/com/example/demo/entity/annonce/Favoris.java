package com.example.demo.entity.annonce;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "favoris")
public class Favoris {

    @Id
    @Column(name = "idfavoris")
    int idfavoris;

    @Column(name = "iduser")
    int iduser;

    @Column(name = "idAnnonce")
    int idannonce;

    @Column(name = "dateabo")
    Date dateabo;

    @Transient
    Annonce annonce;

    //constructeur, getters, setters
}
