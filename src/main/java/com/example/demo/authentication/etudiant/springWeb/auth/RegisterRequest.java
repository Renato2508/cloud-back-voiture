package com.example.demo.authentication.etudiant.springWeb.auth;

public class RegisterRequest {

  private String login;
  private String motDePasse;
  private String nom;
  private String prenom;
  private String role;
  private String notif_token;

  public String getRole(){return  role;}

  public void setRole(String role){this.role = role;}

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getMotDePasse() {
    return motDePasse;
  }

  public void setMotDePasse(String motDePasse) {
    this.motDePasse = motDePasse;
  }

  public String getNom() {
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

  public String getNotif_token() {
    return notif_token;
  }

  public void setNotif_token(String notif_token) {
    this.notif_token = notif_token;
  }
}
