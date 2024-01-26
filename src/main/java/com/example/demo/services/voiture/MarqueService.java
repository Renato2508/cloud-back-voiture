package com.example.demo.services.voiture;

import org.springframework.stereotype.Service;

import java.util.List;

import com.example.demo.entity.voiture.Marque;
import com.example.demo.repository.voiture.MarqueRepository;

@Service
public class MarqueService {
    private final MarqueRepository marqueRepository;

    public MarqueService(MarqueRepository marqueRepository) {
        this.marqueRepository = marqueRepository;
    }

    public List<Marque> findAllMarque(){
        // Utilisez la méthode du référentiel pour récupérer tous les documents
        List<Marque> marque =marqueRepository.findAll();
        return marque;
    }
}
