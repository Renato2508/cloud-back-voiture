package com.example.demo.authentication.etudiant.springWeb.auth;

public class AuthenticationResponse {

  public AuthenticationResponse() {}

  public AuthenticationResponse(String token) {
    setToken(token);
  }

  private String token;

  public String getToken() {
    return token;
  }
  public void setToken(String token) {
    this.token = token;
  }
}
