package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exeption.VoitureException;
import com.example.demo.response.Response;
import com.example.demo.services.annonce.AnnonceService;

@RestController
@RequestMapping("/annonce")
public class AnnonceController {
    @Autowired
    private AnnonceService annonceService;
    private Response response;
    
    @GetMapping("/notvalide")
    public Response annonceNotValide(){
        response = new Response();
       
        try {
            // prendre tous les annonces pas encore valider
            response.setObject(this.annonceService.findAllAnnonceNotValide(0));
            response.setError(false);

            return response;
        } catch (VoitureException e) {
            // en cas d'erreur
            response.setError(true);
            response.setObject(null);
            response.setInformation(e.getVoitureNotFound());
            e.printStackTrace();
            
            return response;
        }catch (Exception e) {
            // en cas d'erreur
            response.setError(true);
            response.setObject(null);
            response.setInformation("erreur interne de server");
            e.printStackTrace();
            
            return response;
        }
    }

    @GetMapping("/valide")
    public Response annonceValidate(@RequestParam int idannonce){
        response = new Response();
       
        try {
            // changer l'etat annonce qu'on veut valider en 3
            this.annonceService.validateAnnonce(idannonce);
            response.setObject(null);
            response.setError(false);

            return response;
        }catch (Exception e) {
            // en cas d'erreur
            response.setError(true);
            response.setObject(null);
            response.setInformation("erreur interne de server");
            e.printStackTrace();
            
            return response;
        }
    }
}
