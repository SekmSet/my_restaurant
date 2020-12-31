package com.my_restaurant.my_restaurant.controller;

import com.my_restaurant.my_restaurant.entity.Avis;
import com.my_restaurant.my_restaurant.service.AvisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;

@Controller
public class AvisController {

    @Autowired
    private AvisService avisService;

    @GetMapping("/avis/ajout")
    public String showAddAvisForm(Avis avis, Model model) {
        return "page/avis/add";
    }

    @PostMapping("/avis/ajout")
    public String addAvis(@Valid Avis avis, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "page/avis/add";
        }

        avisService.save(avis);

        return "redirect:/";
    }
}
