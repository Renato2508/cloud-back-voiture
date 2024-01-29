package com.example.demo.statistiques.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



public class StatCategorie  extends Stat{
    String categorie;
    int annee;
    double chiffreAffaire;
    int vendus;


    
    
    public StatCategorie() {
    }


    public StatCategorie(String categorie, int annee, int vendus, double chiffreAffaire) {
        this.categorie = categorie;
        this.annee = annee;
        this.vendus = vendus;
        this.chiffreAffaire = chiffreAffaire;
    }

    
    public int getAnnee() {
        return annee;
    }
    public void setAnnee(int annee) {
        this.annee = annee;
    }
    public int getVendus() {
        return vendus;
    }
    public void setVendus(int vendus) {
        this.vendus = vendus;
    }
    public double getChiffreAffaire() {
        return chiffreAffaire;
    }
    public void setChiffreAffaire(double chiffreAffaire) {
        this.chiffreAffaire = chiffreAffaire;
    }
    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}
