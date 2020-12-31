package com.my_restaurant.my_restaurant.controller;

import com.my_restaurant.my_restaurant.entity.Menu;
import com.my_restaurant.my_restaurant.service.CarteService;
import com.my_restaurant.my_restaurant.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private CarteService carteService;

    @GetMapping("/admin/menu")
    public String showIndex(Model model) {
        var menu = menuService.findAll();
        model.addAttribute("menus", menu);
        return "admin/menu/index";
    }

    @GetMapping("/admin/menu/{id}")
    public String showOne(@PathVariable("id") long id, Model model) {
        var menu = menuService.findById(id);
        model.addAttribute("menu", menu);
        return "admin/menu/detail";
    }

    @GetMapping("/menu/{id}")
    public String menuOne(@PathVariable("id") long id, Model model) {
        var menu = menuService.findById(id);
        model.addAttribute("menu", menu);
        return "page/menu/detail";
    }


    @GetMapping("/admin/menu/ajout")
    public String showAddMenuForm(Menu menu, Model model) {
        model.addAttribute("cartesList", carteService.findAll());

        return "admin/menu/add";
    }

    @PostMapping("/admin/menu/ajout")
    public String addConfig(@Valid Menu menu, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "admin/menu/add";
        }

        menuService.save(menu);
        return "redirect:/admin/menu";
    }


    @GetMapping("/admin/menu/update/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {

        Menu menu = menuService.findById(id);
        model.addAttribute("menu", menu);
        model.addAttribute("cartesList", carteService.findAll());

        return "admin/menu/update";
    }

    @PostMapping("/admin/menu/update/{id}")
    public String updateMenu(@PathVariable("id") long id, @Valid Menu menu, BindingResult result, Model model) {
        if (result.hasErrors()) {
            menu.setId(id);
            return "admin/menu/update";
        }
        menuService.save(menu);

        return "redirect:/admin/menu";
    }

    @GetMapping("/admin/menu/delete/{id}")
    public String deleteMenu(@PathVariable("id") long id, Model model) {
        Menu menu = menuService.findById(id);
        menuService.delete(menu);
        return "redirect:/admin/menu";
    }

}
