package com.example.demo.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.annex.ModeleInsert;
import com.example.demo.entity.voiture.Categorie;
import com.example.demo.entity.voiture.Marque;
import com.example.demo.entity.voiture.Model;
import com.example.demo.response.Response;
import com.example.demo.services.voiture.CategorieService;
import com.example.demo.services.voiture.MarqueService;
import com.example.demo.services.voiture.ModelService;

@RestController
@RequestMapping("/modele")
@PreAuthorize("hasRole('ADMIN')")
public class ModeleController {
    @Autowired
    private CategorieService categorieService;
    @Autowired
    private MarqueService marqueService;
    @Autowired
    private ModelService modelService;

    @PostMapping("/categorie")
    public ResponseEntity<?> post_categorie(@RequestBody Categorie m){
        Response res;
        try {
            m.setId(UUID.randomUUID().toString());
            categorieService.addCategorie(m);
            res = new Response("Categorie inseree",false,null);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res = new Response(e.getMessage(), true, null);
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/categorie")
    public ResponseEntity<?> get_categorie(){
        Response res;
        try {
            res = new Response("Liste de toutes les catégories",false, categorieService.findAllCategorie());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res = new Response(e.getMessage(), true, null);
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/marque")
    public ResponseEntity<?> post_marque(@RequestBody Marque m){
        Response res;
        try {
            m.setId(UUID.randomUUID().toString());
            marqueService.addMarque(m);
            res = new Response("Marque inseree",false,null);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res = new Response(e.getMessage(), true, null);
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/marque")
    public ResponseEntity<?> get_marque(){
        Response res;
        try {
            res = new Response("Liste de toutes les marques",false, marqueService.findAllMarque());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res = new Response(e.getMessage(), true, null);
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/modele")
    public ResponseEntity<?> post_modele(@RequestBody ModeleInsert m){
        Response res;
        try {
            Model mod = new Model();
            mod.setId(UUID.randomUUID().toString());

            Categorie c = new Categorie(m.getIdcategorie(), m.getNomcategorie());
            Marque ma = new Marque(m.getIdmarque(), m.getNommarque());

            mod.setCategorie(c);
            mod.setMarque(ma);
            mod.setNomModele(m.getNomModele());
            modelService.addModel(mod);
        
            res = new Response("Modele inseré",false,null);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res = new Response(e.getMessage(), true, null);
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
