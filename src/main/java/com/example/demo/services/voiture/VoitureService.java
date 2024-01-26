package com.example.demo.services.voiture;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.voiture.Model;
import com.example.demo.entity.voiture.Voiture;
import com.example.demo.entity.voiture.VoitureInsert;
import com.example.demo.exeption.VoitureException;
import com.example.demo.repository.voiture.ModelRepository;
import com.example.demo.repository.voiture.VoitureRepository;

@Service
public class VoitureService {

    private final VoitureRepository voitureRepository;
    private final ModelRepository modelRepository;

    public VoitureService(VoitureRepository voitureRepository, ModelRepository modelRepository) {
        this.voitureRepository = voitureRepository;
        this.modelRepository = modelRepository;
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
    public void insererNouvelleVoiture(VoitureInsert nouvelleVoiture) throws VoitureException {
        if (nouvelleVoiture != null) {
            Optional<Model> modelUse = modelRepository.findById(nouvelleVoiture.getIdmodele());
            Voiture newVaika = this.createObjectVoiture(nouvelleVoiture, modelUse.get());
            voitureRepository.save(newVaika);
        } else {
            throw new VoitureException();
        }
    }

    public Voiture createObjectVoiture(VoitureInsert nouvelleVoiture, Model modelUse){
        Voiture newVaika = new Voiture(nouvelleVoiture.getImmatriculation(), nouvelleVoiture.getKilometre(), nouvelleVoiture.getPrix(), nouvelleVoiture.getAnnee(), nouvelleVoiture.getDescription(), modelUse);
        return newVaika;
    }

    public List<Voiture> voitureExist(List<Voiture> voiture) throws VoitureException{
        if(voiture.size() <= 0){
            throw new VoitureException();
        }
        return voiture;
    }
    
}

