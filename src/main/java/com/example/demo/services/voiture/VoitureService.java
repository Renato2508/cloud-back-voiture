package com.example.demo.services.voiture;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.example.demo.annex.VoitureFilter;
import com.example.demo.annex.VoitureInsert;
import com.example.demo.entity.pourcentage.Pourcentage;
import com.example.demo.entity.voiture.Model;
import com.example.demo.entity.voiture.Voiture;
import com.example.demo.exeption.VoitureException;
import com.example.demo.repository.pourcentage.PourcentageRepository;
import com.example.demo.repository.voiture.ModelRepository;
import com.example.demo.repository.voiture.VoitureRepository;

@Service
public class VoitureService {

    private final VoitureRepository voitureRepository;
    private final ModelRepository modelRepository;
    private final PourcentageRepository pourcentageRepository;
    private final MongoTemplate mongoTemplate;

    public VoitureService(VoitureRepository voitureRepository, ModelRepository modelRepository, PourcentageRepository pourcentageRepository, MongoTemplate mongoTemplate) {
        this.voitureRepository = voitureRepository;
        this.modelRepository = modelRepository;
        this.pourcentageRepository = pourcentageRepository;
        this.mongoTemplate = mongoTemplate;
    }

    // trouver les voiture par marque
    public List<Voiture> findByImmatriculation(String marque) throws VoitureException {
        // Utilisez la méthode du référentiel pour effectuer une recherche par marque
        List<Voiture> voitures = voitureRepository.findByImmatriculation(marque);
        // Verification si il y une voiture trouver
        voitureExist(voitures);

        return voitures;
    }

    // get tous les voiture existant
    public List<Voiture> findAllVoitures() throws VoitureException {
        // Utilisez la méthode du référentiel pour récupérer tous les documents
        List<Voiture> voitures = voitureRepository.findAll();
        // Verification si il y une voiture trouver
        voitureExist(voitures);
        
        return voitures;
    }

    // insertion d'une nouvelle voiture
    public Voiture insererNouvelleVoiture(VoitureInsert nouvelleVoiture) throws VoitureException {
        if (nouvelleVoiture != null) {
            Optional<Model> modelUse = modelRepository.findById(nouvelleVoiture.getIdmodele());
            Voiture newVaika = this.createObjectVoiture(nouvelleVoiture, modelUse.get());
            return voitureRepository.save(newVaika);
        } else {
            throw new VoitureException();
        }
    }

    public Voiture createObjectVoiture(VoitureInsert nouvelleVoiture, Model modelUse){
        Voiture newVaika = new Voiture(nouvelleVoiture.getImmatriculation(), nouvelleVoiture.getKilometre(), nouvelleVoiture.getPrix(), nouvelleVoiture.getAnnee(), LocalDate.now(), nouvelleVoiture.getDescription(), modelUse);
        //calcule de la commission
        this.getCommission(newVaika);
        return newVaika;
    }

    public List<Voiture> findByFilter(VoitureFilter filter) {
        // Construire la requête en utilisant le filtre
        Query query = new Query();

        if (filter.getIdmarque() != null) {
            query.addCriteria(Criteria.where("modele.marque.id").is(filter.getIdmarque()));
        }
        if (filter.getIdmodel() != null) {
            query.addCriteria(Criteria.where("modele.id").is(filter.getIdmodel()));
        }
        if (filter.getMin() != null && filter.getMax() != null) {
            query.addCriteria(Criteria.where("prix").gte(filter.getMin()).lte(filter.getMax()));
        } else if (filter.getMin() != null) {
            query.addCriteria(Criteria.where("prix").gte(filter.getMin()));
        } else if (filter.getMax() != null) {
            query.addCriteria(Criteria.where("prix").lte(filter.getMax()));
        }
        if (filter.getKilometrage() != null) {
            query.addCriteria(Criteria.where("kilometre").lte(filter.getKilometrage()));
        }
        if (filter.getAnnee() != null) {
            query.addCriteria(Criteria.where("annee").is(filter.getAnnee()));
        }

        // Exécuter la requête
        return mongoTemplate.find(query, Voiture.class);
    }

    public void getCommission(Voiture newVaika){
        Pourcentage pourcentage = this.pourcentageRepository.findByMaxId().get();
        newVaika.setCommission((newVaika.getPrix() * pourcentage.getPourcentage()) / 100);
    }

    public List<Voiture> voitureExist(List<Voiture> voiture) throws VoitureException{
        if(voiture.size() <= 0){
            throw new VoitureException();
        }
        return voiture;
    }
    
}

