package com.example.demo.services.pourcentage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.pourcentage.Pourcentage;
import com.example.demo.repository.pourcentage.PourcentageRepository;

@Service
public class PourcentageService {
    @Autowired
    PourcentageRepository repo;

    public void save(Pourcentage p){
        this.repo.save(p);
    }
}
