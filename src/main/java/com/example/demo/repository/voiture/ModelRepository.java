package com.example.demo.repository.voiture;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.voiture.Model;

@Repository
public interface ModelRepository extends MongoRepository<Model, String>{
    List<Model> findByMarque_Id(String idMarque);
    Optional<Model> findById(String id);
}
