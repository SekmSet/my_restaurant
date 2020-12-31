package com.my_restaurant.my_restaurant.controller;

import com.my_restaurant.my_restaurant.entity.Avis;
import com.my_restaurant.my_restaurant.entity.Config;
import com.my_restaurant.my_restaurant.service.AvisService;
import com.my_restaurant.my_restaurant.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private AvisService avisService;

    @Autowired
    private ConfigService configService;

    @GetMapping("/")
    public String homePage(Model model) {
        long id = 1;
        long limit = 4;

        List<Avis> avis = avisService.getLast(limit);
        Config config = configService.findById(id);

        model.addAttribute("id", id);
        model.addAttribute("avis", avis);
        model.addAttribute("config", config);

        return "index";
    }
}