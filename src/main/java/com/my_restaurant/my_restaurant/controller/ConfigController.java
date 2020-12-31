package com.my_restaurant.my_restaurant.controller;

import com.my_restaurant.my_restaurant.entity.Config;
import com.my_restaurant.my_restaurant.service.AuthService;
import com.my_restaurant.my_restaurant.service.ConfigService;
import com.my_restaurant.my_restaurant.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
public class ConfigController {

    @Autowired
    private ConfigService configService;

    @Autowired
    private AuthService authService;

    @Autowired
    private InMemoryUserDetailsManager inMemoryUserDetailsManager;


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
        try {
            configService.findById(1);
            return "redirect:/admin";
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        return "admin/config/add";
    }

    @PostMapping("/admin/config/ajout")
    public String addConfig(@Valid Config config, BindingResult result, Model model, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        if (result.hasErrors()) {
            return "admin/config/add";
        }

        try {
            configService.findById(1);
            return "redirect:/admin";
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        UserDetails user = User.withUsername("admin")
                .passwordEncoder(pwd -> authService.encryptePassword(pwd))
                .password(config.getMdp())
                .roles("ADMIN")
                .disabled(false)
                .build();
        inMemoryUserDetailsManager.createUser(user);

        String encryptePassword = authService.encryptePassword(config.getMdp());
        config.setMdp(encryptePassword);

        String fileName = UploadService.slugify(multipartFile);
        config.setPhoto(fileName);

        config.setId(1);
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
    public String updateUser(@PathVariable("id") long id, @Valid Config config, BindingResult result, Model model, @RequestParam("image") MultipartFile multipartFile) throws IOException {
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
        } else {
            String encryptePassword = authService.encryptePassword(config.getMdp());
            config.setMdp(encryptePassword);

            UserDetails user = inMemoryUserDetailsManager.loadUserByUsername("admin");
            inMemoryUserDetailsManager.updatePassword(user, encryptePassword);
        }

        Config saveConfig = configService.save(config);

        if (!fileName.isBlank()) {
            String uploadDir = "public/" + Config.UPLOAD_PATH + saveConfig.getId();
            UploadService.saveFile(uploadDir, fileName, multipartFile);
        }

        return "redirect:/admin/config/detail/1";
    }

    @GetMapping("/admin/config/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        Config config = configService.findById(id);
        configService.delete(config);
        return "redirect:/";
    }
}