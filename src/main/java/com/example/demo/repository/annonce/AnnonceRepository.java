package com.example.demo.repository.annonce;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.annonce.Annonce;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnonceRepository extends JpaRepository<Annonce, Integer> {
    List<Annonce> findByEtat(int etat);

    List<Annonce> findByIduser(int iduser);

    @Modifying
    //@Transactional
    @Query("UPDATE Annonce a SET a.etat = :newEtat WHERE a.idannonce = :annonceId")
    void updateEtatById(@Param("annonceId") int annonceId, @Param("newEtat") int newEtat);
}
