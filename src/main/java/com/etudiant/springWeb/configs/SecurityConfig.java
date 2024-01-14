package com.etudiant.springWeb.configs;

import java.util.Arrays;

import com.etudiant.springWeb.requestFilter.JwtRequestFilter;
import com.etudiant.springWeb.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

  @Autowired
  private AuthenticationProvider authenticationProvider;

  @Autowired
  UtilisateurService utilisateurService;
  @Autowired
  private JwtRequestFilter jwtRequestFilter;

  @Bean
  //always public
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
      .csrf(csrf -> csrf.disable()) // Désactive la protection CSRF
      .cors(cors -> cors.configurationSource(this.corsConfigurationSource())) // Configure la gestion des CORS
      .sessionManagement(session ->
        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Définit la politique de gestion des sessions comme étant sans état
      )
      .authorizeHttpRequests(req -> {
        req
          .requestMatchers("/login/**")
          .permitAll() // Autorise toutes les requêtes correspondant à "/auth/**"
          .requestMatchers("/error/**")
          .permitAll() // Autorise toutes les requêtes correspondant à "/error/**"
                .requestMatchers(HttpMethod.GET, "/test/hello").permitAll()

          .anyRequest()
          .authenticated(); // Exige une authentification pour toutes les autres requêtes
      })
      .authenticationProvider(authenticationProvider)
      .addFilterBefore(
        jwtRequestFilter,
        UsernamePasswordAuthenticationFilter.class
      ) // Ajoute un filtre personnalisé (JwtRequestFilter) avant le filtre UsernamePasswordAuthenticationFilter
      .httpBasic(Customizer.withDefaults()) // Utilise l'authentification HTTP de base avec les paramètres par défaut
      .build();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("*")); // Autorise toutes les origines
    configuration.setAllowedMethods(
      Arrays.asList("GET", "POST", "PUT", "DELETE")
    ); // Autorise les méthodes HTTP spécifiées
    configuration.setAllowedHeaders(
      Arrays.asList("authorization", "content-type", "x-auth-token")
    ); // Autorise les en-têtes spécifiés
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration); // Enregistre la configuration CORS pour toutes les URL
    return source; // Retourne la source de configuration CORS
  }
}
