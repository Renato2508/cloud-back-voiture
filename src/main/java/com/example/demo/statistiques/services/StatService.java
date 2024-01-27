package com.example.demo.statistiques.services;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.example.demo.messages.documents.Discussion;
import com.example.demo.messages.documents.Message;
import com.example.demo.messages.repositories.DiscussionRepository;
import com.example.demo.messages.repositories.MessageRepository;
import com.example.demo.statistiques.classes.FiltreCategorie;
import com.example.demo.statistiques.classes.FiltreMarque;
import com.example.demo.statistiques.classes.FiltreStat;
import com.example.demo.statistiques.classes.Stat;
import com.example.demo.statistiques.classes.StatCategorie;
import com.example.demo.statistiques.classes.StatMarque;

@Service
public class StatService {
    

    @Autowired
    private MongoTemplate mongoTemplate;

    
    public static List<StatMarque> sumVentesMarque(List<StatMarque> stats) {
        int vendusTotal = 0;
        double chiffreAffaireTotal = 0.0;

        for (Stat stat : stats) {
            vendusTotal += stat.getVendus();
            chiffreAffaireTotal += stat.getChiffreAffaire();
        }

        StatMarque statTotal = new StatMarque();
        statTotal.setVendus(vendusTotal);
        statTotal.setChiffreAffaire(chiffreAffaireTotal);

        List<StatMarque> stats2 = new ArrayList<StatMarque>(stats);
        stats2.add(statTotal);
        return stats2;
    }

    public static List<StatCategorie> sumVentesCateg(List<StatCategorie> stats) {
        int vendusTotal = 0;
        double chiffreAffaireTotal = 0.0;

        for (Stat stat : stats) {
            vendusTotal += stat.getVendus();
            chiffreAffaireTotal += stat.getChiffreAffaire();
        }

        StatCategorie statTotal = new StatCategorie();
        statTotal.setVendus(vendusTotal);
        statTotal.setChiffreAffaire(chiffreAffaireTotal);
        List<StatCategorie> stats2 = new ArrayList<StatCategorie>(stats);
        stats2.add(statTotal);
        return stats2;
    }

    // STATISTIQUES ANNUELLES PAR MARQUE

    protected List<StatMarque> getStatsByMarque(String marque){
        System.out.println("----> RECHERCHE DES STATS  POUR UNE  MARQUE POUr TOUTES LES ANNEES");
        
        Criteria criteria = Criteria.where("modele.marque.nom").is(marque);
        
        AggregationOperation match = Aggregation.match(criteria);


        AggregationOperation project = Aggregation.project()
                .andExpression("year(dateannonce)").as("annee")
                .and("modele.marque.nom").as("marque")
                .and("commission").as("commission");

       AggregationOperation group = Aggregation.group("annee", "marque")
                .first("annee").as("annee")
                .first("marque").as("marque")
                .sum("commission").as("chiffreAffaire")
                .count().as("vendus");

        

        Aggregation aggregation = Aggregation.newAggregation(match,project, group);
        
        System.out.println("------> Aggregation "+ aggregation);

        AggregationResults<StatMarque> results = mongoTemplate.aggregate(aggregation, "voitures", StatMarque.class);
        
        return results.getMappedResults();
    }

    protected List<StatMarque> getStatMarqueAnnee() {
        System.out.println("----> RECHERCHE DES STATS GENERALES POUR LES MARQUES");
        AggregationOperation project = Aggregation.project()
                .andExpression("year(dateannonce)").as("annee")
                .and("modele.marque.nom").as("marque")
                .and("commission").as("commission");

       AggregationOperation group = Aggregation.group("annee", "marque")
                .first("annee").as("annee")
                .first("marque").as("marque")
                .sum("commission").as("chiffreAffaire")
                .count().as("vendus");

        Aggregation aggregation = Aggregation.newAggregation(project, group);

        System.out.println("------> Aggregation "+ aggregation);


        AggregationResults<StatMarque> results = mongoTemplate.aggregate(aggregation, "voitures", StatMarque.class);

        return results.getMappedResults();
    }

    
    // FONCTION PRINVCIPALE POUR LE CATEGORIES  
    public List<StatMarque> getStatsMarque(FiltreMarque filter){
            System.out.println("-----> INSTANCE DU FILTRE: "+filter.getClass().getName());
            System.out.println("-----> VALEUR ANNEE: "+filter.getAnnee());
            System.out.println("-----> VALEUR MARQUE: "+filter.getMarque());

        List<StatMarque> res = null;
        if(((FiltreMarque)filter).getMarque() != null){
                res = getStatsByMarque(((FiltreMarque)filter).getMarque());
            }

        else{
                
                res = getStatMarqueAnnee();
            }
        return sumVentesMarque(res);
    }


     // STATISTIQUES ANNUELLES PAR CATEGORIE

    protected List<StatCategorie> getStatsByCategorie(String categorie){
        System.out.println("----> RECHERCHE DES STATS  POUR UNE  CATEGORIE POUr TOUTES LES ANNEES");
        
        Criteria criteria = Criteria.where("modele.categorie.nom").is(categorie);
        
        AggregationOperation match = Aggregation.match(criteria);


        AggregationOperation project = Aggregation.project()
                .andExpression("year(dateannonce)").as("annee")
                .and("modele.categorie.nom").as("categorie")
                .and("commission").as("commission");

       AggregationOperation group = Aggregation.group("annee", "categorie")
                .first("annee").as("annee")
                .first("categorie").as("categorie")
                .sum("commission").as("chiffreAffaire")
                .count().as("vendus");

        

        Aggregation aggregation = Aggregation.newAggregation(match,project, group);
        
        System.out.println("------> Aggregation "+ aggregation);

        AggregationResults<StatCategorie> results = mongoTemplate.aggregate(aggregation, "voitures", StatCategorie.class);
        
        return results.getMappedResults();
    }
    

    protected List<StatCategorie> getStatCategorieAnnee(){
        System.out.println("----> RECHERCHE DES STATS GENERALES POUR LES Catégories");
        AggregationOperation project = Aggregation.project()
                .andExpression("year(dateannonce)").as("annee")
                .and("modele.categorie.nom").as("categorie")
                .and("commission").as("commission");

       AggregationOperation group = Aggregation.group("annee", "categorie")
                .first("annee").as("annee")
                .first("categorie").as("categorie")
                .sum("commission").as("chiffreAffaire")
                .count().as("vendus");

        Aggregation aggregation = Aggregation.newAggregation(project, group);

        AggregationResults<StatCategorie> results = mongoTemplate.aggregate(aggregation, "voitures", StatCategorie.class);

        return results.getMappedResults();
    }

    // FONCTION PRINVCIPALE POUR LE CATEGORIES 
    public List<StatCategorie> getStatsCategorie(FiltreCategorie filter){
        System.out.println("-----> INSTANCE DU FILTRE: "+filter.getClass().getName());
            System.out.println("-----> VALEUR ANNEE: "+filter.getAnnee());
            System.out.println("-----> VALEUR CATEGORIE: "+filter.getCategorie());


        if(((FiltreCategorie)filter).getCategorie() != null){
                return sumVentesCateg(getStatsByCategorie(((FiltreCategorie)filter).getCategorie()));
            }
        
            else return sumVentesCateg(getStatCategorieAnnee());
    }
   


}
