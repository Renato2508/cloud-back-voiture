package com.example.demo.services.annonce;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.annonce.Annonce;
import com.example.demo.entity.user.User;
import com.example.demo.entity.voiture.Voiture;
import com.example.demo.exeption.VoitureException;
import com.example.demo.repository.annonce.AnnonceRepository;
import com.example.demo.services.user.UserService;
import com.example.demo.services.voiture.VoitureService;

@Service
public class AnnonceService {
    private final AnnonceRepository annonceRepository;
    private VoitureService voitureService;
    private UserService userService;


    @Autowired
    public AnnonceService(AnnonceRepository annonceRepository, VoitureService voitureService, UserService userService) {
        this.annonceRepository = annonceRepository;
        this.voitureService = voitureService;
        this.userService = userService; 
    }

    public List<Annonce> findAllAnnonceNotValide(int etat) throws VoitureException, InterruptedException, ExecutionException {
        List<Annonce> annonces = this.annonceRepository.findByEtat(etat);

        // Récupérer toutes les voitures et utilisateurs
        Map<String, Voiture> voitureMap = voitureService.findAllVoitures().stream().collect(Collectors.toMap(Voiture::getId, Function.identity()));

        Map<String, User> userMap = userService.getAllUsers().stream().collect(Collectors.toMap(User::getIduser, Function.identity()));

        // Setter les valeurs de voiture et d'utilisateur pour chaque annonce
        for (Annonce annonce : annonces) {
            Voiture voiture = voitureMap.get(annonce.getIdvoiture());
            if (voiture != null) {
                annonce.setVoiture(voiture);
            }

            User user = userMap.get(annonce.getIduser());
            if (user != null) {
                annonce.setUser(user);
            }
        }
        return annonces;
    }

    public void validateAnnonce(int idannonce){
        // je change l'etat en 3 pour la validation
        this.annonceRepository.updateEtatById(idannonce, 3);
    }

}