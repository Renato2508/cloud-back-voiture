package com.example.demo.services.annonce;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.example.demo.authentication.etudiant.springWeb.entities.Utilisateur;
import com.example.demo.authentication.etudiant.springWeb.services.UtilisateurService;
import com.example.demo.entity.annonce.Annonce;
import com.example.demo.entity.user.User;
import com.example.demo.entity.voiture.Voiture;
import com.example.demo.exeption.VoitureException;
import com.example.demo.repository.annonce.AnnonceRepository;
import com.example.demo.services.voiture.VoitureService;

@Service
public class AnnonceService {
    private final AnnonceRepository annonceRepository;
    private VoitureService voitureService;
    private UtilisateurService userService;
  
    public AnnonceService(AnnonceRepository annonceRepository, VoitureService voitureService, UtilisateurService userService) {
        this.annonceRepository = annonceRepository;
        this.voitureService = voitureService;
        this.userService = userService; 
    }

    public List<Annonce> ListeAnnonceFavorisUser(Utilisateur utilisateur) throws VoitureException, InterruptedException, ExecutionException {
        List<Annonce> annonces = utilisateur.getAnnonces();
        // Récupérer toutes les voitures et utilisateurs
        Map<String, Voiture> voitureMap = voitureService.findAllVoitures().stream().collect(Collectors.toMap(Voiture::getId, Function.identity()));
        // Setter les valeurs de voiture et d'utilisateur pour chaque annonce
        for (Annonce annonce : annonces) {
            Voiture voiture = voitureMap.get(annonce.getIdvoiture());
            if (voiture != null) {
                annonce.setVoiture(voiture);
            }
            annonce.setUser(new User(utilisateur.getIdUser(), utilisateur.getEmail(), utilisateur.getNom(), utilisateur.getPrenom()));
        }
        return annonces;
    }

    public List<Annonce> findAllAnnonceNotValide(int etat) throws VoitureException, InterruptedException, ExecutionException {
        List<Annonce> annonces = this.annonceRepository.findByEtat(etat);
        // Récupérer toutes les voitures et utilisateurs
        Map<String, Voiture> voitureMap = voitureService.findAllVoitures().stream().collect(Collectors.toMap(Voiture::getId, Function.identity()));
        Map<Integer, Utilisateur> userMap = userService.getAllUsers().stream().collect(Collectors.toMap(Utilisateur::getIdUser, Function.identity()));
        // Setter les valeurs de voiture et d'utilisateur pour chaque annonce
        for (Annonce annonce : annonces) {
            Voiture voiture = voitureMap.get(annonce.getIdvoiture());
            if (voiture != null) {
                annonce.setVoiture(voiture);
            }
            Utilisateur user = userMap.get(annonce.getIduser());
            if (user != null) {
                annonce.setUser(new User(user.getIdUser(), user.getEmail(), user.getNom(), user.getPrenom()));
            }
        }
        return annonces;
    }

    public List<Annonce> findAllAnnonceValide(int etat) throws VoitureException, InterruptedException, ExecutionException {
        List<Annonce> annonces = this.annonceRepository.findByEtat(etat);
        // Récupérer toutes les voitures et utilisateurs
        Map<String, Voiture> voitureMap = voitureService.findAllVoitures().stream().collect(Collectors.toMap(Voiture::getId, Function.identity()));
        Map<Integer, Utilisateur> userMap = userService.getAllUsers().stream().collect(Collectors.toMap(Utilisateur::getIdUser, Function.identity()));
        // Setter les valeurs de voiture et d'utilisateur pour chaque annonce
        for (Annonce annonce : annonces) {
            Voiture voiture = voitureMap.get(annonce.getIdvoiture());
            if (voiture != null) {
                annonce.setVoiture(voiture);
            }
            Utilisateur user = userMap.get(annonce.getIduser());
            if (user != null) {
                annonce.setUser(new User(user.getIdUser(), user.getEmail(), user.getNom(), user.getPrenom()));
            }
        }
        return annonces;
    }

    public List<Annonce> findAllAnnonceValider() throws VoitureException, InterruptedException, ExecutionException {
        List<Annonce> annonces = this.annonceRepository.findByEtat(1);
        // Récupérer toutes les voitures et utilisateurs
        Map<String, Voiture> voitureMap = voitureService.findAllVoitures().stream().collect(Collectors.toMap(Voiture::getId, Function.identity()));
        Map<Integer, Utilisateur> userMap = userService.getAllUsers().stream().collect(Collectors.toMap(Utilisateur::getIdUser, Function.identity()));
        // Setter les valeurs de voiture et d'utilisateur pour chaque annonce
        for (Annonce annonce : annonces) {
            Voiture voiture = voitureMap.get(annonce.getIdvoiture());
            if (voiture != null) {
                annonce.setVoiture(voiture);
            }
            Utilisateur user = userMap.get(annonce.getIduser());
            if (user != null) {
                annonce.setUser(new User(user.getIdUser(), user.getEmail(), user.getNom(), user.getPrenom()));
            }
        }
        return annonces;
    }

    public List<Annonce> historiqueAnnonceUser(Utilisateur user) throws VoitureException, InterruptedException, ExecutionException {
        List<Annonce> annonces = this.annonceRepository.findByIduser(user.getIdUser());
        // Récupérer toutes les voitures et utilisateurs
        Map<String, Voiture> voitureMap = voitureService.findAllVoitures().stream().collect(Collectors.toMap(Voiture::getId, Function.identity()));
        // Setter les valeurs de voiture et d'utilisateur pour chaque annonce
        for (Annonce annonce : annonces) {
            Voiture voiture = voitureMap.get(annonce.getIdvoiture());
            if (voiture != null) {
                annonce.setVoiture(voiture);
            }               
            annonce.setUser(new User(user.getIdUser(), user.getEmail(), user.getNom(), user.getPrenom()));
        }
        return annonces;
    }

    public List<Annonce> listeAnnonceFilter(List<Voiture> vaika) throws VoitureException, InterruptedException, ExecutionException {
        List<Annonce> annonces = this.annonceRepository.findAll();
        // Récupérer toutes les voitures et utilisateurs
        Map<String, Voiture> voitureMap = vaika.stream().collect(Collectors.toMap(Voiture::getId, Function.identity()));
        // Setter les valeurs de voiture et d'utilisateur pour chaque annonce
        for (Annonce annonce : annonces) {
            Voiture voiture = voitureMap.get(annonce.getIdvoiture());
            if (voiture != null) {
                annonce.setVoiture(voiture);
            }               
        }
        return annonces;
    }

    public void validateAnnonce(int idannonce){ // Modifier l'etat pour valider l'annonce
        this.annonceRepository.updateEtatById(idannonce, 1);
    }

    public void venduAnnonce(int idannonce){ // Modifier l'etat pour dire que le voiture est deja vendu
        this.annonceRepository.updateEtatById(idannonce, 2);
    }

    public Annonce save(Annonce annonce){
        return this.annonceRepository.save(annonce);
    }
}