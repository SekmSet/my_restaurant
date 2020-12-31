package com.my_restaurant.my_restaurant.controller;

import com.my_restaurant.my_restaurant.entity.Avis;
import com.my_restaurant.my_restaurant.service.AvisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private AvisService avisService;

    @GetMapping("/")
    public String homePage(Model model) {
        List<Avis> avis = avisService.getLast(4);
        model.addAttribute("avis", avis);

        return "index";
    }
}