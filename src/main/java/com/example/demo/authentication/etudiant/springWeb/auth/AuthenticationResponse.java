package com.example.demo.authentication.etudiant.springWeb.auth;

public class AuthenticationResponse {

    private String token;
    private int iduser;
    private String nom;
    private String prenom;


  public AuthenticationResponse(String token, int iduser, String nom, String prenom) {
      this.token = token;
      this.iduser = iduser;
      this.nom = nom;
      this.prenom = prenom;
    }

  public AuthenticationResponse() {}

  public AuthenticationResponse(String token) {
    setToken(token);
  }


  public String getToken() {
    return token;
  }
  public void setToken(String token) {
    this.token = token;
  }
  


public String getNom(){
  return nom;
}

public void setNom(String nom) {
  this.nom = nom;
}

public String getPrenom() {
  return prenom;
}

public void setPrenom(String prenom) {
  this.prenom = prenom;
}

public int getIduser() {
  return iduser;
}

public void setIduser(int iduser) {
  this.iduser = iduser;
}
}
