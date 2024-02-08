package com.example.demo.authentication.etudiant.springWeb.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.authentication.etudiant.springWeb.auth.AuthenticationRequest;
import com.example.demo.authentication.etudiant.springWeb.auth.RegisterRequest;
import com.example.demo.authentication.etudiant.springWeb.services.AuthenticationService;
import com.example.demo.response.Response;

@RestController
@RequestMapping("/login")
public class UtilisateurController {

  @Autowired
  private AuthenticationService service;

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
    try {
      service.register(request);
      Response res = new Response("Inscription réussie", false, null);
      return new ResponseEntity<Response>(res, HttpStatus.OK);
    } catch (Exception e) {
      Response res = new Response(e.getMessage(), true, null);
      return new ResponseEntity<Response>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/auth")
  public ResponseEntity<?> auth(@RequestBody AuthenticationRequest request) {
    Response res = null; 
    try {
      res = new Response("200", false, service.authenticate(request));
      return new ResponseEntity<Response>(res, HttpStatus.OK);
    } catch (Exception e) {
      System.out.println("-------> AUTH ERROR");
      res = new Response("Errreur d'authentification", true, null);
      return new ResponseEntity<Response>(res, HttpStatus.UNAUTHORIZED);
    }
    
  
  }
}
