package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.annex.VoitureFilter;
import com.example.demo.annex.VoitureInsert;
import com.example.demo.exeption.VoitureException;
import com.example.demo.response.Response;
import com.example.demo.services.voiture.VoitureService;

@RestController
@RequestMapping("/voitures")
public class VoitureControlle {
    @Autowired
    private VoitureService voitureService;
    private Response response;

    @GetMapping("/all")
    public Response all() {
        response = new Response();
        try {
            response.setObject(this.voitureService.findAllVoitures());
            response.setError(false);
            return response;
        } catch (VoitureException e) {
            response.setObject(null);
            response.setError(false);
            response.setInformation(e.getVoitureNotFound());
            return response;
        } 
    }

    @GetMapping("/immatriculation")
    public Response findByImmatriculation(@RequestParam String marque) {
        response = new Response();
        try {
            response.setObject(this.voitureService.findByImmatriculation(marque));
            response.setError(false);
            return response;
        } catch (VoitureException e) {
            response.setObject(null);
            response.setError(false);
            response.setInformation(e.getVoitureNotFound());
            return response;
        }
    }
    
    @PostMapping("/ajouter")
    public Response ajouterVoiture(@RequestBody VoitureInsert nouvelleVoiture) {
        response = new Response();
        try {
            voitureService.insererNouvelleVoiture(nouvelleVoiture);
            response.setObject(null);
            response.setError(false);
            response.setInformation("Insertion avec succes");
            return response;
        } catch (VoitureException e) {
            response.setError(true);
            response.setInformation(e.getInsertionException());
            e.printStackTrace();
            return response;
        }
    }

    @PostMapping("/filter")
    public Response filter(@RequestBody VoitureFilter nouvelleVoiture) {
        try {
            return  new Response("200", false, voitureService.findByFilter(nouvelleVoiture));
        } catch (Exception e) {
            e.printStackTrace();
            return  new Response("500", true, null);
        }
    }
}
