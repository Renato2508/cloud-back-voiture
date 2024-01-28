package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;

import com.example.demo.annex.VoitureFilter;
import com.example.demo.annex.VoitureInsert;
import com.example.demo.authentication.etudiant.springWeb.entities.Utilisateur;
import com.example.demo.authentication.etudiant.springWeb.services.UtilisateurService;
import com.example.demo.authentication.etudiant.springWeb.tools.JwtUtil;
import com.example.demo.entity.annonce.Annonce;
import com.example.demo.entity.annonce.Favoris;
import com.example.demo.entity.voiture.Voiture;
import com.example.demo.exeption.VoitureException;
import com.example.demo.response.Response;
import com.example.demo.services.annonce.AnnonceService;
import com.example.demo.services.annonce.FavorisServices;
import com.example.demo.services.voiture.VoitureService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
@RequestMapping("/annonce")
public class AnnonceController {
    @Autowired
    private AnnonceService annonceService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private VoitureService voitureService;
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private FavorisServices favorisServices;


    @PostMapping("ajout")
    @PreAuthorize("hasRole('USER')") // sauvegarder une annonce
    public Response saveAnnonce(@RequestHeader(name = "Authorization") String token, @RequestBody VoitureInsert nouvelleVoiture) {
        try {
            Voiture voiture = voitureService.insererNouvelleVoiture(nouvelleVoiture);
            Utilisateur sender = jwtUtil.extractUser(token);
            Annonce annonce = this.annonceService.save(new Annonce(sender.getIdUser(), voiture.getId(), 0));
            return new Response("200", false, annonce);
        } catch (VoitureException e) {
            e.printStackTrace();
            return new Response("400", false, null);
        }
    }
    
    @GetMapping("/notvalide")
    @PreAuthorize("hasRole('ADMIN')")// liste des annonce non valider
    public ResponseEntity<?> annonceNotValide(){
        Response res = null;
        try {
            // prendre tous les annonces pas encore valider
            res = new Response("200", false, this.annonceService.findAllAnnonceNotValide(0));
            return new ResponseEntity<Response>(res, HttpStatus.OK);
        } catch (VoitureException e) {
            // en cas d'erreur
            e.printStackTrace();
            res = new Response(e.getMessage(), true, null);
            return new ResponseEntity<Response>(res, HttpStatus.INTERNAL_SERVER_ERROR);

        }catch (Exception e) {
            // en cas d'erreur     
            res = new Response("400", true, null);
            return new ResponseEntity<Response>(res, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/favoris") // liste des annonce favoris 
    @PreAuthorize("hasRole('USER')")
    public Response annonceFavoris(@RequestHeader(name = "Authorization") String token){
        try {
            Utilisateur sender = jwtUtil.extractUser(token);
            return new Response("200", false, this.annonceService.ListeAnnonceFavorisUser(utilisateurService.findById(sender.getIdUser())));
        }catch (Exception e) {
            // en cas d'erreur     
            e.printStackTrace();       
            return new Response("400", true, null);
        }
    }

    @GetMapping("/addfavoris") // ajouter une annonce au favoris
    @PreAuthorize("hasRole('USER')")
    public Response addFavoris(@RequestHeader(name = "Authorization") String token, int idannonce){
        try {
            // Ajouter une annonce au favoris
            Utilisateur sender = jwtUtil.extractUser(token);
            return new Response("200", false, favorisServices.save(new Favoris(sender.getIdUser(), idannonce, LocalDate.now())));
        }catch (Exception e) {
            // en cas d'erreur     
            e.printStackTrace();       
            return new Response("400", true, null);
        }
    }

    @GetMapping("/updatevendu") // change le status d'une annonce en vendu
    @PreAuthorize("hasRole('USER')")
    public Response updatevendu(@RequestParam int idannonce){
        try {
            // change l'etat d'une annonce oe vendu ou pas
            this.annonceService.venduAnnonce(idannonce);
            return new Response("200", false, null);
        }catch (Exception e) {
            // en cas d'erreur
            e.printStackTrace();
            return new Response("400", true, null);
        }
    }

    @GetMapping("/annonceuser")
    @PreAuthorize("hasRole('USER')") // liste des annonce fais par l'utilsateur
    public Response historiqueAnnonce(@RequestHeader(name = "Authorization") String token){
        try {
            Utilisateur sender = jwtUtil.extractUser(token);
            return new Response("200", false,  this.annonceService.historiqueAnnonceUser(sender));
        } catch (VoitureException e) {
            // en cas d'erreur
            e.printStackTrace();
            return new Response("400", true, null);
        }catch (Exception e) {
            // en cas d'erreur
            e.printStackTrace();
            return new Response("400", true, null);
        }
    }

    @GetMapping("/valide")
    @PreAuthorize("hasRole('ADMIN')") // valider une annonce
    public Response annonceValidate(@RequestParam int idannonce){       
        try {
            // changer l'etat annonce qu'on veut valider en 3
            this.annonceService.validateAnnonce(idannonce);
            return new Response("200", false, null);
        }catch (Exception e) {
            // en cas d'erreur
            e.printStackTrace();
            return new Response("400", true, null);
        }
    }

    @PostMapping("/filter")
    public Response filter(@RequestBody VoitureFilter nouvelleVoiture) {
        try {
            return  new Response("200", false, annonceService.listeAnnonceFilter(voitureService.findByFilter(nouvelleVoiture)));
        } catch (Exception e) {
            e.printStackTrace();
            return  new Response("500", true, null);
        }
    }
}
