package com.my_restaurant.my_restaurant.controller;

import com.my_restaurant.my_restaurant.entity.Config;
import com.my_restaurant.my_restaurant.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ConfigController {

    @Autowired
    private ConfigService configService;

    @GetMapping("/admin/config")
    public String showIndex() {
        return "admin/config/index";
    }

    @GetMapping("/admin/config/ajout")
    public String showSignUpForm(Config config) {
        return "admin/config/add";
    }

    @PostMapping("/admin/config/ajout")
    public String addConfig(@Valid Config config, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "admin/config/add";
        }

        configService.save(config);
        return "redirect:/";
    }

    @GetMapping("/admin/config/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {

        Config config = configService.findById(id);
        model.addAttribute("config", config);

        return "admin/config/update";
    }

    @PostMapping("/admin/config/edit/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid Config config, BindingResult result, Model model) {
        if (result.hasErrors()) {
            config.setId(id);
            return "admin/config/update";
        }
        configService.save(config);

        return "redirect:/";
    }

    @GetMapping("/admin/config/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        Config config = configService.findById(id);
        configService.delete(config);
        return "redirect:/";
    }
}