package com.example.demo.authentication.etudiant.springWeb.controller;


import org.springframework.beans.factory.annotation.Autowired;
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
  public Response auth(@RequestBody AuthenticationRequest request) {
    try {
      return new Response("200", false, service.authenticate(request));
    } catch (Exception e) {
      return new Response("400", true, null);
    }
    
  
  }
}
