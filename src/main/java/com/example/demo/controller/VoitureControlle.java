package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.response.Response;
import com.example.demo.services.voiture.CategorieService;
import com.example.demo.services.voiture.MarqueService;
import com.example.demo.services.voiture.ModelService;

@RestController
@RequestMapping("/voitures")
public class VoitureControlle {
    @Autowired
    private CategorieService categorieService;
    @Autowired
    private MarqueService marqueService;
    @Autowired
    private ModelService modelService;

    @GetMapping("/marque")
    public Response allMarque() {
        try {
            return new Response("200", false, this.marqueService.findAllMarque());
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("400", true, null);
        } 
    }

    @GetMapping("/model")
    public Response allModel() {
        try {
            return new Response("200", false, this.modelService.findAllModel());
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("400", true, null);
        } 
    }

    @GetMapping("/categorie")
    public Response allCategorie() {
        try {
            return new Response("200", false, this.categorieService.findAllCategorie());
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("400", true, null);
        } 
    }
    
    
}
