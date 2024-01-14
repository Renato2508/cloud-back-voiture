package com.etudiant.springWeb.services;

import java.util.Optional;

import com.etudiant.springWeb.entities.Utilisateur;
import com.etudiant.springWeb.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UtilisateurService {

  @Autowired
  private UtilisateurRepository utilisateurRepository;

  public Optional<Utilisateur> findByNameAndPassword(Utilisateur utilisateur) {
    System.out.println("tafididtra");

    Optional<Utilisateur> user = utilisateurRepository.findByLoginAndMotdepasse(
      utilisateur.getLogin(),
      utilisateur.getMotdepasse()
    );
    System.out.println(user);
    if (user.isPresent()) {
      System.out.println(user.get());
    }
    return user;
  }
}
