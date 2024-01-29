package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.pourcentage.Pourcentage;
import com.example.demo.response.Response;
import com.example.demo.services.pourcentage.PourcentageService;
import com.example.demo.services.voiture.CategorieService;
import com.example.demo.services.voiture.MarqueService;
import com.example.demo.services.voiture.ModelService;

@RestController
@RequestMapping("/detail")
public class DetailController {
    @Autowired
    private MarqueService marqueService;
    @Autowired
    private ModelService modelServies;
    @Autowired
    private CategorieService categorieServies;
    @Autowired 
    PourcentageService  perc_service; 


    private Response response;

    @PostMapping("/commission")
    @PreAuthorize("hasRole('ADMIN')")    
    public ResponseEntity<?> maj_pourcentage(@RequestBody Pourcentage percent){
        Response res =null;
        try {
        perc_service.save(percent);
        res = new Response("Mise à jour de la commission prélevée", false, percent);
        return new ResponseEntity<Response>(res, HttpStatus.OK);
    } catch (Exception e) {
        e.printStackTrace();
        res = new Response(e.getMessage(),true, null);
        return new ResponseEntity<Response>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }

@GetMapping("/marca")
public ResponseEntity<?> allMarqueCategorie() {
    Response res;
    Object[] value = new Object[2];
    try {
        value[0] = this.marqueService.findAllMarque();
        value[1] = this.categorieServies.findAllCategorie();
        res = new Response("Liste des marques", false, value);
        return new ResponseEntity<>(res, HttpStatus.OK);
    } catch (Exception e) {
        res = new Response("Erreur à la récupération des marques",true, null);
        return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

@GetMapping("/model")
public ResponseEntity<?> allModelCorrespondante(@RequestParam String idmarque) {
    Response res;
    try {
        res = new Response("récupération de toutes les modeles ",false , modelServies.findModelUse(idmarque));
        return new ResponseEntity<>(res, HttpStatus.OK);
    } catch (Exception e) {
        res = new Response("erreur à la récupération des modèeles", true, null);
        return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
   
}
