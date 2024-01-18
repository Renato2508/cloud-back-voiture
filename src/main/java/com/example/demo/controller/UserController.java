package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.user.User;
import com.example.demo.exeption.UserException;
import com.example.demo.response.Response;
import com.example.demo.services.user.UserService;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserService userService;
    private Response response;
    
    @PostMapping("/register")
    public Response saveUser(@RequestBody User user){
        response = new Response();
       
        try {
            // prendre tous les annonces pas encore valider
            response.setObject(this.userService.saveUser(user));
            response.setError(false);

            return response;
        } catch (UserException e) {
            // en cas d'erreur
            response.setError(true);
            response.setObject(null);
            response.setInformation(e.getUserNotFound());
            e.printStackTrace();
            
            return response;
        } catch (Exception e) {
            // en cas d'erreur
            response.setError(true);
            response.setObject(null);
            response.setInformation("erreur interne de server");
            e.printStackTrace();
            
            return response;
        }
    }

    @GetMapping("/users")
    public Response getAllUsers(){
        response = new Response();
       
        try {
            // prendre tous les annonces pas encore valider
            response.setObject(this.userService.getAllUsers());
            response.setError(false);

            return response;
        } catch (Exception e) {
            // en cas d'erreur
            response.setError(true);
            response.setObject(null);
            e.printStackTrace();
            
            return response;
        } 
    }
}
