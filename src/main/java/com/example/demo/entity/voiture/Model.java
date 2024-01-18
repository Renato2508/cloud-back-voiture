package com.example.demo.entity.voiture;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "model")
public class Model {
    @Id
    @JsonProperty("_id")
    private String id;
    
    @JsonProperty("categorie")
    private Categorie categorie;

    @JsonProperty("marque")
    private Marque marque;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    // Constructeurs, getters, setters...
}
