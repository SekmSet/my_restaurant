package com.my_restaurant.my_restaurant.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {
    @GetMapping("/reviews")
    public String reviews() {
        return String.format("REVIEWS PAGE!");
    }
}