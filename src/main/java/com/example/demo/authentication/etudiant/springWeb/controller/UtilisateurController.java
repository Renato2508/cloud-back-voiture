package com.example.demo.authentication.etudiant.springWeb.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.authentication.etudiant.springWeb.auth.AuthenticationRequest;
import com.example.demo.authentication.etudiant.springWeb.auth.RegisterRequest;
import com.example.demo.authentication.etudiant.springWeb.services.AuthenticationService;
import com.example.demo.authentication.etudiant.springWeb.tools.Util;

@RestController
@RequestMapping("/login")
public class UtilisateurController {

  @Autowired
  private AuthenticationService service;

  @PostMapping("/register")
  public ResponseEntity<Map<String, Object>> register(
    @RequestBody RegisterRequest request
  ) {
    Map<String, Object> response = Util.getDefaultResponse();
    response.put("response", service.register(request));
    return new ResponseEntity<Map<String, Object>>(
      response,
      HttpStatusCode.valueOf(200)
    );
  }

  @PostMapping("/auth")
  public ResponseEntity<Map<String, Object>> auth(
    @RequestBody AuthenticationRequest request
  ) {
    Map<String, Object> response = Util.getDefaultResponse();
    try {
        response.put("response", service.authenticate(request));
        return new ResponseEntity<Map<String, Object>>(response, HttpStatusCode.valueOf(200));

    } catch (Exception e) {
      ResponseEntity<Map<String, Object>> res = new ResponseEntity<Map<String, Object>>(new HashMap<String,Object>(), HttpStatus.UNAUTHORIZED);
      return res;
    }
    
  
  }
}
