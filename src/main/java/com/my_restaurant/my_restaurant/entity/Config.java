package com.my_restaurant.my_restaurant.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Config {
    public static final String UPLOAD_PATH = "upload/config/banniere-";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Le nom du restaurant est requis")
    private String name;

    @NotBlank(message = "L'URL du site est requis")
    private String url;

    @NotBlank(message = "Le mot de passe est requis")
    private String mdp;

     private String email;

     private String numberFix;

     private String numberPort;

     private String adresse;

     private String ville;

     private String cp;

     private String liens;

     private String boutons;

    @NotBlank(message = "La bannière de votre site est requise")
    private String photo;


    public Config() {

    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumberFix() {
        return numberFix;
    }

    public void setNumberFix(String numberFix) {
        this.numberFix = numberFix;
    }

    public String getNumberPort() {
        return numberPort;
    }

    public void setNumberPort(String numberPort) {
        this.numberPort = numberPort;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    public String getLiens() {
        return liens;
    }

    public void setLiens(String liens) {
        this.liens = liens;
    }

    public String getBoutons() {
        return boutons;
    }

    public void setBoutons(String boutons) {
        this.boutons = boutons;
    }

    @Transient
    public String getPhotosImagePath() {
        if (photo == null) {
            return null;
        }

        return "/" + UPLOAD_PATH + id + "/" + photo;
    }
}
