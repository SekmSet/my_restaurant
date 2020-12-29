package com.my_restaurant.my_restaurant.controller;

import com.my_restaurant.my_restaurant.entity.Config;
import com.my_restaurant.my_restaurant.repository.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Controller
public class AdminController {

    @Autowired
    private ConfigRepository ConfigRepository;

    @GetMapping("/admin")
    public String admin() {
        return "ADMIN PAGE !";
    }

    @GetMapping("/signup")
    public String showSignUpForm(Config config) {
        return "admin/add-config";
    }

    @PostMapping("/addconfig")
    public String addConfig(@Valid Config config, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-config";
        }

        ConfigRepository.save(config);
        return "redirect:/";
    }
}