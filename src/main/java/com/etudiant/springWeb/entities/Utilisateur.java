package com.etudiant.springWeb.entities;

import java.util.Collection;

import java.util.List;

import com.etudiant.springWeb.tools.Role;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Utilisateur implements UserDetails{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idutilisateur")
  int idUtilisateur;

  String login;
  String motdepasse;
  @Enumerated(EnumType.STRING)
  Role role;

  public int getIdUtilisateur() {
    return idUtilisateur;
  }

  public void setIdUtilisateur(int idUtilisateur) {
    this.idUtilisateur = idUtilisateur;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getMotdepasse() {
    return motdepasse;
  }

  public void setMotdepasse(String motdepasse) {
    this.motdepasse = motdepasse;
  }

  @Override
  public String toString() {
    return "Utilisateur [login=" + login + ", motdepasse=" + motdepasse + "]";
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name())) ;
  }

  @Override
  public String getPassword() {
    return getMotdepasse();
  }

  @Override
  public String getUsername() {
    return getLogin();
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
}
