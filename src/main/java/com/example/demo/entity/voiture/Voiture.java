package com.example.demo.entity.voiture;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Id;

import java.time.LocalDate;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "voitures")
public class Voiture {

    @Id
    @JsonProperty("_id")
    private String id;
    
    @JsonProperty("immatriculation")
    private String immatriculation;

    @JsonProperty("kilometre")
    private int kilometre;

    @JsonProperty("prix")
    private double prix;

    @JsonProperty("annee")
    private LocalDate annee;

    @JsonProperty("description")
    private String description;

    @JsonProperty("modele")
    private Model modele;

    // Constructeurs, getters, setters...

    public Voiture(String immatriculation, int kilometre, double prix, LocalDate annee, String description, Model modele) {
        this.immatriculation = immatriculation;
        this.kilometre = kilometre;
        this.prix = prix;
        this.annee = annee;
        this.description = description;
        this.modele = modele;
    }

    public Voiture() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public int getKilometre() {
        return kilometre;
    }

    public void setKilometre(int kilometre) {
        this.kilometre = kilometre;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public LocalDate getAnnee() {
        return annee;
    }

    public void setAnnee(LocalDate annee) {
        this.annee = annee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Model getModele() {
        return modele;
    }

    public void setModele(Model modele) {
        this.modele = modele;
    }
}


