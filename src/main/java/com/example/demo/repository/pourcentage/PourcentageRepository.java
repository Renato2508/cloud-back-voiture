package com.example.demo.repository.pourcentage;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.pourcentage.Pourcentage;

public interface PourcentageRepository extends JpaRepository<Pourcentage, Integer> {
       
    @Query("SELECT p FROM Pourcentage p WHERE p.idpourcentage = (SELECT MAX(p2.idpourcentage) FROM Pourcentage p2)")
    Optional<Pourcentage> findByMaxId();
}

