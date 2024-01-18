package com.example.demo.repository.voiture;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.voiture.Categorie;

@Repository
public interface CategorieRepository extends MongoRepository<Categorie, String> {
   
}
