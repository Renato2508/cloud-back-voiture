package com.example.demo.statistiques.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor

public class StatMarque extends Stat{
    String marque;

    int annee;
    int vendus;
    double chiffreAffaire;
    
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
    public String getMarque() {
        return marque;
    }
    public void setMarque(String marque) {
        this.marque = marque;
    }
}
