package com.example.demo.entity.voiture;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "voitures")
public class Voiture {
    @JsonProperty("images")
    private List<byte[]> images;

    @Id
    @JsonProperty("_id")
    private String id;
    
    @JsonProperty("immatriculation")
    private String immatriculation;

    @JsonProperty("kilometre")
    private int kilometre;

    @JsonProperty("prix")
    private double prix;

    @JsonProperty("anneesortie")
    private int annee;

    @JsonProperty("dateaanonce")
    private LocalDate dateaanonce;

    @JsonProperty("commission")
    private double commission;

    @JsonProperty("description")
    private String description;

    @JsonProperty("modele")
    private Model modele;

    // Constructeurs, getters, setters...

    public Voiture(String immatriculation, int kilometre, double prix, int annee, LocalDate dateaanonce, String description, Model modele, List<byte[]>images) {
        this.immatriculation = immatriculation;
        this.dateaanonce = dateaanonce;
        this.kilometre = kilometre;
        this.prix = prix;
        this.annee = annee;
        this.description = description;
        this.modele = modele;
        this.images = images;
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

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
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

    public LocalDate getDateaanonce() {
        return dateaanonce;
    }

    public void setDateaanonce(LocalDate dateaanonce) {
        this.dateaanonce = dateaanonce;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }
}


