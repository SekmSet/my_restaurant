package com.my_restaurant.my_restaurant.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String hello() {
        return String.format("HOME PAGE!");
    }
}