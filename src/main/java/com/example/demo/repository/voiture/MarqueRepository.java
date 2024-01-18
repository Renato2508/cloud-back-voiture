package com.example.demo.repository.voiture;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.voiture.Marque;

@Repository
public interface MarqueRepository extends MongoRepository<Marque, String>{
    
}
