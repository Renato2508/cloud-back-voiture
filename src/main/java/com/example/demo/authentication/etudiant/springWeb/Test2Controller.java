package com.example.demo.authentication.etudiant.springWeb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.authentication.etudiant.springWeb.tools.JwtUtil;

@RestController
@RequestMapping("/protected")
public class Test2Controller {
    @Autowired
    private JwtUtil  jwtUtil;
    
    @GetMapping("/hello")
    public String hello(@RequestHeader(name = "Authorization") String token) {
        String t = token.substring(7);
        System.out.println("***** TPKEN *****");
        System.out.println(t);
        return "Namoaka id by token:  "+ this.jwtUtil.extractID(t);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/salut")
    public String protected_hello() {
        return "PRETECTED SALUT LES AMOUREUX CLASS";
    }
}
