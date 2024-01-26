package com.example.demo.annex;

public class VoitureFilter {
     String idmarque;
     String idmodel;
     Double min;
     Double max;
     Integer kilometrage;
     Integer annee;

     public String getIdmarque() {
          return idmarque;
     }
     public void setIdmarque(String idmarque) {
          this.idmarque = idmarque;
     }
     public String getIdmodel() {
          return idmodel;
     }
     public void setIdmodel(String idmodel) {
          this.idmodel = idmodel;
     }
     public Double getMin() {
          return min;
     }
     public void setMin(Double min) {
          this.min = min;
     }
     public Double getMax() {
          return max;
     }
     public void setMax(Double max) {
          this.max = max;
     }
     public Integer getKilometrage() {
          return kilometrage;
     }
     public void setKilometrage(Integer kilometrage) {
          this.kilometrage = kilometrage;
     }
     public Integer getAnnee() {
          return annee;
     }
     public void setAnnee(Integer annee) {
          this.annee = annee;
     }
}
