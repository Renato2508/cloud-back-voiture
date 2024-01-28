package com.example.demo.entity.voiture;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Document(collection = "model")
@Data
public class Model {
    @Id
    @JsonProperty("_id")
    private String id;
    
    @JsonProperty("categorie")
    private Categorie categorie;

    @JsonProperty("marque")
    private Marque marque;

    @JsonProperty("nom")
    private String nomModele;

    
    // Constructeurs, getters, setters...
}
