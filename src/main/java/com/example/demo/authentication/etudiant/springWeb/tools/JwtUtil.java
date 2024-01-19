package com.example.demo.authentication.etudiant.springWeb.tools;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
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

@Service
public class JwtUtil {

  // to make sure the info was not modified
  private final String SECRET_KEY =
    "DvxMWzlQ2d6zSQ77EseNcGI1x0hhpCVJwtXBIx4c7uUlDSSRCD4kBXFyzEY2zLdN";

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

  public String generateToken(
    Map<String, Object> extraClaims,
    UserDetails userDetails
  ) {
    String role = userDetails
            .getAuthorities()
            .stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toSet())
            .iterator()
            .next();
    extraClaims.put("role", role);

    Utilisateur u = (Utilisateur)userDetails;
    int id = u.getIdUser();
    extraClaims.put("iduser", id);
    return Jwts
      .builder()
      .setClaims(extraClaims)
      .setSubject(userDetails.getUsername())
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + 1000 * 600 * 64))
      .signWith(getSignInKey(), SignatureAlgorithm.HS256)
      .compact();
  }

  public int extractID(String token) {
    return extractClaimByName(token, "iduser", Integer.class);
  }

  public <T> T extractClaimByName(String token, String claimName, Class<T> valueType) {
    final Claims allClaims = extractAllClaims(token);
    return allClaims.get(claimName, valueType);
}

  public String extractLogin(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public boolean validateToken(String token)
    throws AuthenticationCredentialsNotFoundException {
    try {
      Jwts
        .parserBuilder()
        .setSigningKey(SECRET_KEY)
        .build()
        .parseClaimsJws(token);
      return true;
    } catch (Exception ex) {
      throw new AuthenticationCredentialsNotFoundException(
        "Invalid token",
        ex.fillInStackTrace()
      );
    }
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    System.out.println("!!!!! TOKEN ");
    System.out.println(token);
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
