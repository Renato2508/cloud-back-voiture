package com.example.demo.repository.voiture;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.voiture.Voiture;

import java.util.List;

@Repository
public interface VoitureRepository extends MongoRepository<Voiture, String> {
    List<Voiture> findByImmatriculation(String marque);
}

