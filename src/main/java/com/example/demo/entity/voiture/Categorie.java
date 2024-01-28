package com.example.demo.entity.voiture;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categorie")
@AllArgsConstructor
public class Categorie {
    @Id
    @JsonProperty("_id")
    private String id;
    
    @JsonProperty("nom")
    private String nom;

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

    // Constructeurs, getters, setters...
}
