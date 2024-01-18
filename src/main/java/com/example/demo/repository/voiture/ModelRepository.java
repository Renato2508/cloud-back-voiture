package com.example.demo.repository.voiture;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.voiture.Model;

@Repository
public interface ModelRepository extends MongoRepository<Model, String>{
    List<Model> findByMarque_IdAndCategorie_Id(String idMarque, String idCategorie);
}
