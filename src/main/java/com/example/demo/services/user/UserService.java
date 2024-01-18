package com.example.demo.services.user;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.example.demo.entity.user.User;
import com.example.demo.exeption.UserException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.Query;

@Service
public class UserService {
    private final String collection_name = "users";
    
    //j'indique que le parametre ne doit pas etre nom null
    public String saveUser(User user) throws UserException, Exception {
        if (user == null) {
            throw new UserException();
        }
    
        Firestore dbFirestore = FirestoreClient.getFirestore();
    
        // Création d'une nouvelle référence de document avec un ID automatiquement généré
        DocumentReference docRef = dbFirestore.collection(collection_name).document();
    
        // Récupération de l'ID unique généré
        String uniqueId = docRef.getId();
    
        // Assignation de l'ID à l'objet User
        user.setIduser(uniqueId);
    
        // Enregistrement de l'utilisateur dans la référence de document
        docRef.set(user);
    
        // Récupération de la date de mise à jour pour renvoyer en réponse
        return user.getIduser();
    }
    
    

    public List<User> getAllUsers() throws InterruptedException, ExecutionException {

        Firestore dbFirestore = FirestoreClient.getFirestore();
    
        // Obtention de tous les documents de la collection "users"
        ApiFuture<QuerySnapshot> querySnapshot = dbFirestore.collection(collection_name).get();
    
        // Traitement des résultats
        List<User> userList = new ArrayList<>();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            User user = document.toObject(User.class);
            userList.add(user);
        }
    
        return userList;
    }
    

    public List<User> getUsersByName(String targetName) throws InterruptedException, ExecutionException {

        Firestore dbFirestore = FirestoreClient.getFirestore();

        // Construction de la requête pour filtrer les documents par le champ "nom"
        CollectionReference usersCollection = dbFirestore.collection(collection_name);
        Query query = usersCollection.whereEqualTo("nom", targetName);

        // Exécution de la requête
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        // Traitement des résultats
        List<User> userList = new ArrayList<>();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            User user = document.toObject(User.class);
            userList.add(user);
        }

        return userList;
    }

}
