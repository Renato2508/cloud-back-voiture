package com.example.demo.authentication.etudiant.springWeb.requestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.authentication.etudiant.springWeb.tools.JwtUtil;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private UserDetailsService userDetailsService;

  public String extractTokenFromHeader(String header) {
    return header.substring(7);
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain ) throws ServletException, IOException {
    String authenticationHeader = request.getHeader("Authorization");
    String jwtToken;
    if (authenticationHeader == null || !authenticationHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }
    jwtToken = extractTokenFromHeader(authenticationHeader);
    String login = jwtUtil.extractLogin(jwtToken); //extract the login from JWT

    // is not connected yet or the token is valid
    if (login != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      // from database
      UserDetails user = this.userDetailsService.loadUserByUsername(login);
      if (jwtUtil.isTokenValid(jwtToken, user)) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }
    filterChain.doFilter(request, response);
  }
}
