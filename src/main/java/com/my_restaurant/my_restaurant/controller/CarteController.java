package com.my_restaurant.my_restaurant.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarteController {
    @GetMapping("/carte")
    public String carte() {
        return String.format("MENU PAGE !");
    }
}