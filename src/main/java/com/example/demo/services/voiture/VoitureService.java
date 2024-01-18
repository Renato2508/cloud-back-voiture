package com.example.demo.services.voiture;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.voiture.Voiture;
import com.example.demo.exeption.VoitureException;
import com.example.demo.repository.voiture.VoitureRepository;

@Service
public class VoitureService {

    private final VoitureRepository voitureRepository;

    @Autowired
    public VoitureService(VoitureRepository voitureRepository) {
        this.voitureRepository = voitureRepository;
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
    public void insererNouvelleVoiture(Voiture nouvelleVoiture) throws VoitureException {
        if (nouvelleVoiture != null) {
            voitureRepository.save(nouvelleVoiture);
        } else {
            throw new VoitureException();
        }
    }

    public List<Voiture> voitureExist(List<Voiture> voiture) throws VoitureException{
        if(voiture.size() <= 0){
            throw new VoitureException();
        }
        return voiture;
    }
    
}

