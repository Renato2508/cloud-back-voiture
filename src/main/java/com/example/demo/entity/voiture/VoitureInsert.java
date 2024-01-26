package com.example.demo.entity.voiture;

import java.time.LocalDate;

public class VoitureInsert {    
    private String immatriculation;

    private int kilometre;

    private double prix;

    private LocalDate annee;

    private String description;

    private String idmodele;

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

    public String getIdmodele() {
        return idmodele;
    }

    public void setIdmodele(String idmodele) {
        this.idmodele = idmodele;
    }

    
}
