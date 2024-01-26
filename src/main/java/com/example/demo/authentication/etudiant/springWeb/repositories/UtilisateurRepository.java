package com.example.demo.authentication.etudiant.springWeb.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.authentication.etudiant.springWeb.entities.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
  Optional<Utilisateur> findByEmailAndMdp(String email, String mdp);
  Optional<Utilisateur> findByEmail(String email);
}
