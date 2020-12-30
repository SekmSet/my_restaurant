package com.my_restaurant.my_restaurant.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Le nom du menu est requis")
    private String name;

    @NotBlank(message = "Le prix (en €) du menu est requis")
    private Integer prix;

    @ManyToMany(mappedBy = "menu")
    Set<Plat> plat = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "menu_carte",
            joinColumns = @JoinColumn(name = "carte_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id"))
    Set<Carte> carte = new HashSet<>();

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

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    public Set<Plat> getPlat() {
        return plat;
    }

    public void setPlat(Set<Plat> plat) {
        this.plat = plat;
    }

    public Set<Carte> getCarte() {
        return carte;
    }

    public void setCarte(Set<Carte> carte) {
        this.carte = carte;
    }
}
