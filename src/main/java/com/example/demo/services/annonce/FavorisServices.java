package com.example.demo.services.annonce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.annonce.Favoris;
import com.example.demo.repository.annonce.FavorisRepository;

@Service
public class FavorisServices {
    private final FavorisRepository favorisRepository;

    @Autowired
    public FavorisServices(FavorisRepository favorisRepository) {
        this.favorisRepository = favorisRepository;
    }

    public List<Favoris> findAll(){
        return this.favorisRepository.findAll();
    }
}
