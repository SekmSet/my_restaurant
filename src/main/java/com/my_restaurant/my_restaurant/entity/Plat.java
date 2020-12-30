package com.my_restaurant.my_restaurant.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Plat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Le nom du plat est requis")
    private String name;

    @NotBlank(message = "La description du plat est requise")
    private  String description;

    @NotBlank(message = "La photo du plat est requise")
    private String photo;

    @NotBlank(message = "Le prix (en €) du plat est requis")
    private Integer prix;

    @NotBlank(message = "Les alergènes du plat sont requis")
    private String alergenes;

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
}
