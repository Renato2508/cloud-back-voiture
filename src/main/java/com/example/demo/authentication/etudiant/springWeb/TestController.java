package com.example.demo.authentication.etudiant.springWeb;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/free")
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "PROTECTED ADMIN Hello";
    }

    @GetMapping("/free_hello")
    public String free_ello() {
        return "FREE Hello";
    }
}
