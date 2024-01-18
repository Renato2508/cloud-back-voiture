package com.example.demo.entity.voiture;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "marque")
public class Marque {
    
    @Id
    @JsonProperty("_id")
    private String id;

    @JsonProperty("nom")
    private String nom;

    @JsonProperty("logo")
    private int logo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

     // Constructeurs, getters, setters...
}
