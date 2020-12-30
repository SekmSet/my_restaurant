package com.my_restaurant.my_restaurant.controller;

import com.my_restaurant.my_restaurant.entity.Carte;
import com.my_restaurant.my_restaurant.service.CarteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CarteController {

    @Autowired
    private CarteService carteService;

    @GetMapping("/admin/carte")
    public String showIndex(Model model) {
        var cartes = carteService.findAll();
        model.addAttribute("cartes", cartes);
        return "admin/carte/index";
    }

    @GetMapping("/admin/carte/{id}")
    public String showOne(@PathVariable("id") long id, Model model) {
        var carte = carteService.findById(id);
        model.addAttribute("carte", carte);
        return "admin/carte/detail";
    }

    @GetMapping("/admin/carte/ajout")
    public String showAddCarteForm(Carte carte) {
        return "admin/carte/add";
    }

    @PostMapping("/admin/carte/ajout")
    public String addConfig(@Valid Carte carte, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "admin/carte/add";
        }

        carteService.save(carte);
        return "redirect:/admin/carte";
    }


    @GetMapping("/admin/carte/update/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {

        Carte carte = carteService.findById(id);
        model.addAttribute("carte", carte);

        return "admin/carte/update";
    }

    @PostMapping("/admin/carte/update/{id}")
    public String updateCarte(@PathVariable("id") long id, @Valid Carte carte, BindingResult result, Model model) {
        if (result.hasErrors()) {
            carte.setId(id);
            return "admin/carte/update";
        }
        carteService.save(carte);

        return "redirect:/admin/carte";
    }

    @GetMapping("/admin/carte/delete/{id}")
    public String deleteCarte(@PathVariable("id") long id, Model model) {
        Carte carte = carteService.findById(id);
        carteService.delete(carte);
        return "redirect:/admin/carte";
    }

}
