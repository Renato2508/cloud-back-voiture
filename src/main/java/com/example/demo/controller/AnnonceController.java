package com.example.demo.controller;

import org.hibernate.SessionFactory;
import org.hibernate.engine.jdbc.spi.JdbcServices;
import org.hibernate.engine.jdbc.spi.SqlStatementLogger;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.stat.Statistics;
import org.hibernate.stat.spi.StatisticsImplementor;
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

import jakarta.persistence.EntityManagerFactory;

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
    @Autowired
    private EntityManagerFactory entityManagerFactory;


    @PostMapping("nouvelle_annonce")
    @PreAuthorize("hasRole('USER')") // sauvegarder une annonce
    public ResponseEntity<?>saveAnnonce(@RequestHeader(name = "Authorization") String token, @RequestBody VoitureInsert nouvelleVoiture) {
        Response res;
        try {
            Voiture voiture = voitureService.insererNouvelleVoiture(nouvelleVoiture);
            Utilisateur sender = jwtUtil.extractUser(token);
            Annonce annonce = this.annonceService.save(new Annonce(sender.getIdUser(), voiture.getId(), 0));
            res = new Response("200", false, annonce);
            return new ResponseEntity<Response>(res, HttpStatus.OK);
        } catch (VoitureException e) {
            e.printStackTrace();
            res = new Response("503", false, null);
            return new ResponseEntity<Response>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/notvalide")
    @PreAuthorize("hasRole('ADMIN')")// liste des annonce non valider
    public ResponseEntity<?> annonceNotValide(){
        System.out.println("Recherche des annonces notvalide");
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
            res = new Response(e.getMessage(), true, null);
            return new ResponseEntity<Response>(res, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/favoris") // liste des annonce favoris 
@PreAuthorize("hasRole('USER')")
public ResponseEntity<?> annonceFavoris(@RequestHeader(name = "Authorization") String token){
    Response res;
    try {
        Utilisateur sender = jwtUtil.extractUser(token);
        res = new Response("200", false, this.annonceService.ListeAnnonceFavorisUser(utilisateurService.findById(sender.getIdUser())));
        return new ResponseEntity<Response>(res, HttpStatus.OK);
    } catch (Exception e) {
        // en cas d'erreur     
        e.printStackTrace();       
        res = new Response("400", true, null);
        return new ResponseEntity<Response>(res, HttpStatus.BAD_REQUEST);
    }
}

@GetMapping("/addfavoris") // ajouter une annonce au favoris
@PreAuthorize("hasRole('USER')")
public ResponseEntity<?> addFavoris(@RequestHeader(name = "Authorization") String token, int idannonce){
    Response res;
    try {
        // Ajouter une annonce au favoris
        Utilisateur sender = jwtUtil.extractUser(token);
        res = new Response("200", false, favorisServices.save(new Favoris(sender.getIdUser(), idannonce, LocalDate.now())));
        return new ResponseEntity<Response>(res, HttpStatus.OK);
    } catch (Exception e) {
        // en cas d'erreur     
        e.printStackTrace();       
        res = new Response("400", true, null);
        return new ResponseEntity<Response>(res, HttpStatus.BAD_REQUEST);
    }
}

@GetMapping("/updatevendu") // change le status d'une annonce en vendu
@PreAuthorize("hasRole('USER')")
public ResponseEntity<?> updatevendu(@RequestParam int idannonce){
    Response res;
    try {
        // change l'etat d'une annonce oe vendu ou pas
        this.annonceService.venduAnnonce(idannonce);
        res = new Response("200", false, null);
        return new ResponseEntity<Response>(res, HttpStatus.OK);
    } catch (Exception e) {
        // en cas d'erreur
        e.printStackTrace();
        res = new Response("400", true, null);
        return new ResponseEntity<Response>(res, HttpStatus.BAD_REQUEST);
    }
}

@GetMapping("/annonceuser")
@PreAuthorize("hasRole('USER')") // liste des annonce fais par l'utilsateur
public ResponseEntity<?> historiqueAnnonce(@RequestHeader(name = "Authorization") String token){
    Response res;
    try {
        Utilisateur sender = jwtUtil.extractUser(token);
        res = new Response("200", false,  this.annonceService.historiqueAnnonceUser(sender));
        return new ResponseEntity<Response>(res, HttpStatus.OK);
    } catch (VoitureException e) {
        // en cas d'erreur
        e.printStackTrace();
        res = new Response("400", true, null);
        return new ResponseEntity<Response>(res, HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
        // en cas d'erreur
        e.printStackTrace();
        res = new Response("400", true, null);
        return new ResponseEntity<Response>(res, HttpStatus.BAD_REQUEST);
    }
}

@GetMapping("/valide")
@PreAuthorize("hasRole('ADMIN')") // valider une annonce
public ResponseEntity<?> annonceValidate(@RequestParam int idannonce){       
    Response res;
    try {
        // changer l'etat annonce qu'on veut valider en 3
        this.annonceService.validateAnnonce(idannonce);
        res = new Response("200", false, null);
        return new ResponseEntity<Response>(res, HttpStatus.OK);
    } catch (Exception e) {
        // en cas d'erreur
        e.printStackTrace();
        res = new Response("400", true, null);
        return new ResponseEntity<Response>(res, HttpStatus.BAD_REQUEST);
    }
}

@PostMapping("/filter")
public ResponseEntity<?> filter(@RequestBody VoitureFilter nouvelleVoiture) {
    Response res;
    try {
        res = new Response("200", false, annonceService.listeAnnonceFilter(voitureService.findByFilter(nouvelleVoiture)));
        return new ResponseEntity<Response>(res, HttpStatus.OK);
    } catch (Exception e) {
        e.printStackTrace();
        res = new Response("500", true, null);
        return new ResponseEntity<Response>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

}
