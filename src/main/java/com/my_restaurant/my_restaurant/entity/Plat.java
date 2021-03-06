package com.my_restaurant.my_restaurant.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Plat {
    public static final String UPLOAD_PATH = "upload/admin/plat-";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Le nom du plat est requis")
    private String name;

    @NotBlank(message = "La description du plat est requise")
    private  String description;

    private String photo;

    @Min(value = 1, message = "Le prix du plat est de minimum 1€")
    private Integer prix;

    @NotBlank(message = "Les alergènes du plat sont requis")
    private String alergenes;

    @ManyToMany
    @JoinTable(
            name = "plat_menu",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "plat_id"))
    Set<Menu> menu = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    public String getAlergenes() {
        return alergenes;
    }

    public void setAlergenes(String alergenes) {
        this.alergenes = alergenes;
    }

    public Set<Menu> getMenu() {
        return menu;
    }

    public void setMenu(Set<Menu> menu) {
        this.menu = menu;
    }

    public void removeMenu(Menu menu) {
        this.menu.remove(menu);
    }

    @Transient
    public String getPhotosImagePath() {
        if (photo == null) {
            return null;
        }

        return "/" + UPLOAD_PATH + id + "/" + photo;
    }
}
