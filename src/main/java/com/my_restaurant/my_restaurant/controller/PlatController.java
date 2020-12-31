package com.my_restaurant.my_restaurant.controller;

import com.my_restaurant.my_restaurant.entity.Plat;
import com.my_restaurant.my_restaurant.service.MenuService;
import com.my_restaurant.my_restaurant.service.PlatService;
import com.my_restaurant.my_restaurant.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class PlatController {

    @Autowired
    private PlatService platService;

    @Autowired
    private MenuService menuService;

    @GetMapping("/admin/plat")
    public String showIndex(Model model) {
        var plat = platService.findAll();
        model.addAttribute("plats", plat);
        return "admin/plat/index";
    }

    @GetMapping("/plat")
    public String plat(Model model) {
        var plat = platService.findAll();
        model.addAttribute("plats", plat);
        return "page/plat/index";
    }

    @GetMapping("/admin/plat/{id}")
    public String showOne(@PathVariable("id") long id, Model model) {
        var plat = platService.findById(id);
        model.addAttribute("plat", plat);
        return "admin/plat/detail";
    }

    @GetMapping("/plat/{id}")
    public String platOne(@PathVariable("id") long id, Model model) {
        var plat = platService.findById(id);
        model.addAttribute("plat", plat);
        return "page/plat/detail";
    }

    @GetMapping("/admin/plat/ajout")
    public String showAddPlatForm(Plat plat, Model model) {
        model.addAttribute("menuList", menuService.findAll());
        return "admin/plat/add";
    }

    @PostMapping("/admin/plat/ajout")
    public String addConfig(@Valid Plat plat, BindingResult result, Model model, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        if (result.hasErrors()) {
            return "admin/plat/add";
        }

        String fileName = UploadService.slugify(multipartFile);
        plat.setPhoto(fileName);

        Plat savePlat = platService.save(plat);

        String uploadDir = "public/" + Plat.UPLOAD_PATH + savePlat.getId();
        UploadService.saveFile(uploadDir, fileName, multipartFile);

        return "redirect:/admin/plat";
    }


    @GetMapping("/admin/plat/update/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {

        Plat plat = platService.findById(id);
        model.addAttribute("plat", plat);
        model.addAttribute("menuList", menuService.findAll());

        return "admin/plat/update";
    }

    @PostMapping("/admin/plat/update/{id}")
    public String updatePlat(@PathVariable("id") long id, @Valid Plat plat, BindingResult result, Model model,  @RequestParam("image") MultipartFile multipartFile) throws IOException {
        if (result.hasErrors()) {
            plat.setId(id);
            return "admin/plat/update";
        }

        String fileName = UploadService.slugify(multipartFile);
        plat.setPhoto(fileName);

        Plat oldPlat = platService.findById(id);

        if (plat.getPhoto().isBlank()) {
            plat.setPhoto(oldPlat.getPhoto());
        }

        Plat savePlat = platService.save(plat);

        if (!fileName.isBlank()) {
            String uploadDir = "public/" + Plat.UPLOAD_PATH + savePlat.getId();
            UploadService.saveFile(uploadDir, fileName, multipartFile);
        }

        return "redirect:/admin/plat";
    }

    @GetMapping("/admin/plat/delete/{id}")
    public String deletePlat(@PathVariable("id") long id, Model model) {
        Plat plat = platService.findById(id);
        platService.delete(plat);
        return "redirect:/admin/plat";
    }

}
