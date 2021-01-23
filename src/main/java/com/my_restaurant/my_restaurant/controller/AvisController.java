package com.my_restaurant.my_restaurant.controller;

import com.my_restaurant.my_restaurant.entity.Avis;
import com.my_restaurant.my_restaurant.service.AvisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/admin/avis")
    public String indexAvis(Model model){
        var avis = avisService.findAll();
        model.addAttribute("avis", avis);
        return "admin/avis/index";
    }

    @GetMapping("/admin/avis/{id}")
    public String showOne(@PathVariable("id") long id, Model model) {
        var avi = avisService.findById(id);
        model.addAttribute("avi", avi);
        return "admin/avi/detail";
    }

    @GetMapping("/admin/avis/update/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {

        Avis avis = avisService.findById(id);
        model.addAttribute("avis", avis);

        return "admin/avis/update";
    }

    @PostMapping("/admin/avis/update/{id}")
    public String updateCarte(@PathVariable("id") long id, @Valid Avis avis, BindingResult result, Model model) {
        if (result.hasErrors()) {
            avis.setId(id);
            return "admin/avis/update";
        }
        avisService.save(avis);

        return "redirect:/admin/avis";
    }

    @GetMapping("/admin/avis/delete/{id}")
    public String deleteCarte(@PathVariable("id") long id, Model model) {
        Avis avi = avisService.findById(id);

        avisService.delete(avi);
        return "redirect:/admin/avis";
    }
}
