package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.response.Response;
import com.example.demo.services.voiture.CategorieService;
import com.example.demo.services.voiture.MarqueService;
import com.example.demo.services.voiture.ModelService;

@RestController
@RequestMapping("/detail")
public class DetailController {
    @Autowired
    private MarqueService marqueService;
    @Autowired
    private ModelService modelServies;
    @Autowired
    private CategorieService categorieServies;

    private Response response;

    @GetMapping("/marca")
    public Response allMarqueCategorie() {
        response = new Response();
        Object[] value = new Object[2];
        try {
            value[0] = this.marqueService.findAllMarque();
            value[1] = this.categorieServies.findAllCategorie();
            response.setObject(value);
            response.setError(false);
            return response;
        } catch (Exception e) {
            response.setObject(null);
            response.setError(false);
            return response;
        } 
    }  
    
    @GetMapping("/model")
    public Response allModelCorrespondante(@RequestParam String idmarque) {
        response = new Response();
        try {
            response.setObject(modelServies.findModelUse(idmarque));
            response.setError(false);
            return response;
        } catch (Exception e) {
            response.setObject(null);
            response.setError(false);
            return response;
        } 
    }   
}
