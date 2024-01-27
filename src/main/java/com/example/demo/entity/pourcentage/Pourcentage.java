package com.example.demo.entity.pourcentage;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pourcentage")
public class Pourcentage {
    @Id
    @Column(name = "idpourcentage")
    int idpourcentage;

    @Column(name = "pourcentage")
    double pourcentage;

       public int getIdpourcentage() {
              return idpourcentage;
       }

       public void setIdpourcentage(int idpourcentage) {
              this.idpourcentage = idpourcentage;
       }

       public double getPourcentage() {
              return pourcentage;
       }

       public void setPourcentage(double pourcentage) {
              this.pourcentage = pourcentage;
       }  
}
