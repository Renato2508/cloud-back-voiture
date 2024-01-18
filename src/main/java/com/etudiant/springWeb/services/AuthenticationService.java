package com.etudiant.springWeb.services;

import com.etudiant.springWeb.auth.AuthenticationRequest;
import com.etudiant.springWeb.auth.AuthenticationResponse;
import com.etudiant.springWeb.auth.RegisterRequest;
import com.etudiant.springWeb.entities.Utilisateur;
import com.etudiant.springWeb.repositories.UtilisateurRepository;
import com.etudiant.springWeb.tools.JwtUtil;
import com.etudiant.springWeb.tools.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

  @Autowired
  private UtilisateurRepository utilisateurRepository;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public AuthenticationResponse register(RegisterRequest request) {
    var user = new Utilisateur();
    user.setEmail(request.getLogin());
    user.setMdp(passwordEncoder.encode(request.getMotDePasse()));
    String role = request.getRole();
    if(role.compareToIgnoreCase("admin") == 0){
      user.setRole(Role.ROLE_ADMIN);
    }else{
      user.setRole(Role.ROLE_USER);
    }
    utilisateurRepository.save(user);
    var jwtToken = jwtUtil.generateToken(user);
    return new AuthenticationResponse(jwtToken);
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    System.out.println(utilisateurRepository.findByEmail(request.getLogin()));
    System.out.println(request.getLogin());
    System.out.println(request.getMotDePasse());
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        request.getLogin(),
        request.getMotDePasse()
      )
    );
    var user = utilisateurRepository
      .findByEmail(request.getLogin())
      .orElseThrow();
    var token = jwtUtil.generateToken(user);

    AuthenticationResponse response = new AuthenticationResponse(token);
    return response;
  }
}
