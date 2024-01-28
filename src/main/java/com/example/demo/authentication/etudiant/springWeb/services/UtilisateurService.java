package com.example.demo.authentication.etudiant.springWeb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.authentication.etudiant.springWeb.entities.Utilisateur;
import com.example.demo.authentication.etudiant.springWeb.repositories.UtilisateurRepository;

import jakarta.transaction.Transactional;


@Service
public class UtilisateurService {

  @Autowired
  private UtilisateurRepository utilisateurRepository;

  
  public Optional<Utilisateur> findByNameAndPassword(Utilisateur utilisateur) {
    Optional<Utilisateur> user = utilisateurRepository.findByEmailAndMdp(
      utilisateur.getEmail(),
      utilisateur.getMdp()
    );
    if (user.isPresent()) {
      System.out.println(user.get());
    }
    return user;
  }

@Transactional
  public List<Utilisateur> getAllUsers(){
    return utilisateurRepository.findAll();
  }

  public Utilisateur findById(int iduser){
      return utilisateurRepository.findById(iduser).get();
  }
}
