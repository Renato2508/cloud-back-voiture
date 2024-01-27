package com.example.demo.statistiques.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.response.Response;
import com.example.demo.services.annonce.AnnonceService;
import com.example.demo.statistiques.classes.FiltreCategorie;
import com.example.demo.statistiques.classes.FiltreMarque;
import com.example.demo.statistiques.classes.FiltreStat;
import com.example.demo.statistiques.classes.Stat;
import com.example.demo.statistiques.classes.StatCategorie;
import com.example.demo.statistiques.classes.StatMarque;
import com.example.demo.statistiques.services.StatService;

@RestController
@RequestMapping("/stats")
@PreAuthorize("hasRole('ADMIN')")
public class StatController {
    @Autowired
    private StatService statService;
    private Response response;

    @GetMapping("/statsCategorie")
    public ResponseEntity<?> getStatsCategorie(@RequestBody FiltreCategorie filter){
        response = new Response();
        List<StatCategorie> l = statService.getStatsCategorie(filter);
        response.setObject(l);
        response.setError(false);
        response.setInformation("Statistiques par categorie par année");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/statsMarque")
    public ResponseEntity<?> getStats(@RequestBody FiltreMarque filter){
        response = new Response();
        List<StatMarque> l = statService.getStatsMarque(filter);
        response.setObject(l);
        response.setError(false);
        response.setInformation("Statistiques par marque par année");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
