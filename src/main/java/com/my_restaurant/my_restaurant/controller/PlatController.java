package com.my_restaurant.my_restaurant.controller;

import com.my_restaurant.my_restaurant.entity.Plat;
import com.my_restaurant.my_restaurant.service.PlatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class PlatController {

    @Autowired
    private PlatService platService;

    @GetMapping("/admin/plat")
    public String showIndex(Model model) {
        var plat = platService.findAll();
        model.addAttribute("plats", plat);
        return "admin/plat/index";
    }

    @GetMapping("/admin/plat/{id}")
    public String showOne(@PathVariable("id") long id, Model model) {
        var plat = platService.findById(id);
        model.addAttribute("plat", plat);
        return "admin/plat/detail";
    }

    @GetMapping("/admin/plat/ajout")
    public String showAddPlatForm(Plat plat) {
        return "admin/plat/add";
    }

    @PostMapping("/admin/plat/ajout")
    public String addConfig(@Valid Plat plat, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "admin/plat/add";
        }

        platService.save(plat);
        return "redirect:/admin/plat";
    }


    @GetMapping("/admin/plat/update/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {

        Plat plat = platService.findById(id);
        model.addAttribute("plat", plat);

        return "admin/plat/update";
    }

    @PostMapping("/admin/plat/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid Plat plat, BindingResult result, Model model) {
        if (result.hasErrors()) {
            plat.setId(id);
            return "admin/plat/update";
        }
        platService.save(plat);

        return "redirect:/admin/plat";
    }

    @GetMapping("/admin/plat/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        Plat plat = platService.findById(id);
        platService.delete(plat);
        return "redirect:/admin/plat";
    }

}
