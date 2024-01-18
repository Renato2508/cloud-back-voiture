package com.example.demo.exeption;

public class VoitureException extends Exception {
    private final String voitureNotFound = "voiture non trouver";
    private final String insertionException = "verifier votre donner";

    public String getVoitureNotFound() {
        return voitureNotFound;
    }

    public String getInsertionException() {
        return insertionException;
    }
    
}
