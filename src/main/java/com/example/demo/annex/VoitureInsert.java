package com.example.demo.annex;

import java.util.List;

import lombok.Data;

@Data
public class VoitureInsert {    
    private String immatriculation;

    private int kilometre;

    private double prix;

    private int annee;

    private String description;

    private String idmodele;

    private List<String> images;

    
}
