package com.etudiant.springWeb;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/protected")
public class Test2Controller {

    @GetMapping("/hello")
    public String hello() {
        return "PROTECTED HELLO CLASS";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/salut")
    public String protected_hello() {
        return "PRETECTED SALUT LES AMOUREUX CLASS";
    }
}
