package com.example.demo.exeption;

public class UserException extends Exception {
    public final String userNotFound = "pas d'utilisateur trouver";

    public String getUserNotFound() {
        return userNotFound;
    }

    
}
