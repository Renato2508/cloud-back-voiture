package com.etudiant.springWeb.repositories;

import com.etudiant.springWeb.entities.Utilisateur;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository
  extends JpaRepository<Utilisateur, Integer> {
  Optional<Utilisateur> findByEmailAndMdp(
    String email,
    String mdp
  );
  Optional<Utilisateur> findByEmail(String email);

}
