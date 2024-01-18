package com.example.demo.services.voiture;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.voiture.Model;
import com.example.demo.repository.voiture.ModelRepository;

@Service
public class ModelService {
        private final ModelRepository modelRepository;

    @Autowired
    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public List<Model> findAllModel(){
        // Utilisez la méthode du référentiel pour récupérer tous les documents
        List<Model> model = modelRepository.findAll();
        return model;
    }

    public List<Model> findModelUse(String idmarque, String idcategorie){
        // Utilisez la méthode du référentiel pour récupérer tous les documents
        List<Model> model = modelRepository.findByMarque_IdAndCategorie_Id(idmarque , idcategorie);
        return model;
    }
}
