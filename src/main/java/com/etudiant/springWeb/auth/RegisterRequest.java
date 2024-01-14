package com.etudiant.springWeb.auth;

import com.etudiant.springWeb.tools.Role;

public class RegisterRequest {

  private String login;
  private String motDePasse;
  private String role;

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
}
