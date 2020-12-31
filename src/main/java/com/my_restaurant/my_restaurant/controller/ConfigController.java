package com.my_restaurant.my_restaurant.controller;

import com.github.slugify.Slugify;
import com.my_restaurant.my_restaurant.entity.Config;
import com.my_restaurant.my_restaurant.service.ConfigService;
import com.my_restaurant.my_restaurant.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Objects;

@Controller
public class ConfigController {

    @Autowired
    private ConfigService configService;

    @GetMapping("/admin")
    public String showIndex() {
        return "admin/index";
    }

    @GetMapping("/admin/config/detail/{id}")
    public String showConfig(@PathVariable("id") long id, Model model) {
       Config config = configService.findById(id);
       model.addAttribute("config", config);

        return "admin/config/detail";
    }

    @GetMapping("/admin/config/ajout")
    public String showSignUpForm(Config config) {
        return "admin/config/add";
    }

    @PostMapping("/admin/config/ajout")
    public String addConfig(@Valid Config config, BindingResult result, Model model, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        if (result.hasErrors()) {
            return "admin/config/add";
        }

        String fileName = UploadService.slugify(multipartFile);
        config.setPhoto(fileName);

        Config saveConfig = configService.save(config);

        String uploadDir = "public/" + Config.UPLOAD_PATH + saveConfig.getId();
        UploadService.saveFile(uploadDir, fileName, multipartFile);

        return "redirect:/";
    }

    @GetMapping("/admin/config/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {

        Config config = configService.findById(id);
        model.addAttribute("config", config);

        return "admin/config/update";
    }

    @PostMapping("/admin/config/edit/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid Config config, BindingResult result, Model model, @RequestParam("image") MultipartFile multipartFile) throws IOException  {
        if (result.hasErrors()) {
            config.setId(id);
            return "admin/config/update";
        }

        String fileName = UploadService.slugify(multipartFile);
        config.setPhoto(fileName);

        Config oldConfig = configService.findById(id);
        if (config.getPhoto().isBlank()) {
            config.setPhoto(oldConfig.getPhoto());
        }
        if (config.getMdp().isBlank()) {
            config.setMdp(oldConfig.getMdp());
        }

        Config saveConfig = configService.save(config);

        if (!fileName.isBlank()) {
            String uploadDir = "public/" + Config.UPLOAD_PATH + saveConfig.getId();
            UploadService.saveFile(uploadDir, fileName, multipartFile);
        }

        return "redirect:/";
    }

    @GetMapping("/admin/config/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        Config config = configService.findById(id);
        configService.delete(config);
        return "redirect:/";
    }
}