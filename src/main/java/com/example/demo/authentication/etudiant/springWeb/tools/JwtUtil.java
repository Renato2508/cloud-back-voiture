package com.example.demo.authentication.etudiant.springWeb.tools;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.demo.authentication.etudiant.springWeb.entities.Utilisateur;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtil {

  // to make sure the info was not modified
  private final String SECRET_KEY =
    "DvxMWzlQ2d6zSQ77EseNcGI1x0hhpCVJwtXBIx4c7uUlDSSRCD4kBXFyzEY2zLdN";


  // extraire un Utilisateur à partir du token
  public Utilisateur extractUser(String token){
      String t = token.substring(7);
      int id1 = this.extractID(t);
      Utilisateur sender = new Utilisateur();
      sender.setIdUser(id1);
      sender.setNom(this.extractNom(t));
      sender.setPrenom(this.extractPrenom(t));

      return sender;

  }

  public Boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    String login = extractLogin(token);
    return (
      login.equals(userDetails.getUsername()) && (!isTokenExpired(token))
    );
  }

  public String generateToken(UserDetails userDetails) {
    return generateToken(new HashMap<String, Object>(), userDetails);
  }

  public String generateToken(Map<String, Object> extraClaims,UserDetails userDetails) {
    String role = userDetails
            .getAuthorities()
            .stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toSet())
            .iterator()
            .next();
    extraClaims.put("role", role);
    
    // ajout des différents extraclaims nécessaires
    Utilisateur u = (Utilisateur)userDetails;
    int id = u.getIdUser();
    String nom = u.getNom();
    String prenom = u.getPrenom();

    extraClaims.put("iduser", id);
    extraClaims.put("nom", nom);
    extraClaims.put("prenom", prenom);


    return Jwts
      .builder()
      .setClaims(extraClaims)
      .setSubject(userDetails.getUsername())
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + 1000 * 600 * 64))
      .signWith(getSignInKey(), SignatureAlgorithm.HS256)
      .compact();
  }


  // extraction des divers claims
  public int extractID(String token) {
    return extractClaimByName(token, "iduser", Integer.class);
  }

  public String extractNom(String token) {
    return extractClaimByName(token, "nom", String.class);
  }

  public String extractPrenom(String token) {
    return extractClaimByName(token, "prenom", String.class);
  }

  public <T> T extractClaimByName(String token, String claimName, Class<T> valueType) {
    final Claims allClaims = extractAllClaims(token);
    return allClaims.get(claimName, valueType);
}

  public String extractLogin(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public boolean validateToken(String token) throws AuthenticationCredentialsNotFoundException {
    try {
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        Jwts
            .parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token);
        return true;
    } catch (Exception ex) {
        throw new AuthenticationCredentialsNotFoundException("Invalid token", ex.fillInStackTrace());
    }
}

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts
      .parserBuilder()
      .setSigningKey(getSignInKey())
      .build()
      .parseClaimsJws(token)
      .getBody();
  }

  public Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
