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
  public Response register(@RequestBody RegisterRequest request) {
    try {
      service.register(request);
      return new Response("200", false, null);
    } catch (Exception e) {
      return new Response("400", true, null);
    }
  }

  @PostMapping("/auth")
  public ResponseEntity<?> auth(@RequestBody AuthenticationRequest request) {
    Response res = null; 
    try {
      res = new Response("200", false, service.authenticate(request));
      return new ResponseEntity<Response>(res, HttpStatus.OK);
    } catch (Exception e) {
      res = new Response("Errreur d'authentification", true, null);
      return new ResponseEntity<Response>(res, HttpStatus.UNAUTHORIZED);
    }
    
  
  }
}
