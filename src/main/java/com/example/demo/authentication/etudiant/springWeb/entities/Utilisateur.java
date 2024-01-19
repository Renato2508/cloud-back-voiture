package com.example.demo.authentication.etudiant.springWeb.entities;

import java.util.Collection;

import java.util.List;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.authentication.etudiant.springWeb.tools.Role;

@Entity
@Table(name = "utilisateur")
public class Utilisateur implements UserDetails{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "iduser")
  int idUser;

  @Column(name = "nom")
  String nom;

  @Column(name = "prenom")
  String prenom;

  @Column(name = "email")
  String email;

  @Column(name = "mdp")
  String mdp;

  @Enumerated(EnumType.STRING)
  Role role;

  
  
  @Override
  public String toString() {
    return "Utilisateur [login=" + email + ", motdepasse=" + mdp + "]";
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name())) ;
  }

  @Override
  public String getPassword() {
    return getMdp();
  }

  @Override
  public String getUsername() {
    return getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }




  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public int getIdUser() {
    return idUser;
  }

  public void setIdUser(int idUser) {
    this.idUser = idUser;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getMdp() {
    return mdp;
  }

  public void setMdp(String mdp) {
    this.mdp = mdp;
  }
}
