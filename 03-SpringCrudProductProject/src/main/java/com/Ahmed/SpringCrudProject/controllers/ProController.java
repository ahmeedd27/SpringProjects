/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Ahmed.SpringCrudProject.controllers;

import com.Ahmed.SpringCrudProject.dao.ProRepo;
import com.Ahmed.SpringCrudProject.entities.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class ProController {
    
    private ProRepo pr;
    @Autowired
    public ProController(ProRepo pr){
        this.pr=pr;
    }
    
    @GetMapping("/products")
    public String showProducts(Model m){
       List<Product> listProducts=pr.findAll();
       m.addAttribute("listpro", listProducts);
       return "list-pro";
    }
    
}
