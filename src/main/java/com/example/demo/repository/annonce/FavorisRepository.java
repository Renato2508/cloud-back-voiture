package com.example.demo.repository.annonce;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.annonce.Favoris;

@Repository
public interface FavorisRepository extends JpaRepository<Favoris, Integer>{
    
}
