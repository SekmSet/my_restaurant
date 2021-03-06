package com.my_restaurant.my_restaurant.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

@Entity
public class Config {
    public static final String UPLOAD_PATH = "upload/config/banniere-";

    @Id
    private long id;

    @NotBlank(message = "Le nom du restaurant est requis")
    private String name;

    @NotBlank(message = "L'URL du site est requis")
    private String url;

    //@NotBlank(message = "Le mot de passe est requis")
//    @Pattern.List({
//            @Pattern(regexp = "(?=.*[0-9])", message = "Password must contain one digit."),
//            @Pattern(regexp = "(?=.*[a-z])", message = "Password must contain one lowercase letter."),
//            @Pattern(regexp = "(?=.*[A-Z])", message = "Password must contain one uppercase letter."),
//            @Pattern(regexp = "(?=\\S+$)", message = "Password must contain no whitespace."),
//            //@Pattern(regexp = ".*^\\w{8,}$", message="8 caracters")
//    })
    private String mdp;

    private String email;

    private String numberFix;

    private String numberPort;

    private String adresse;

    private String ville;

    private String cp;

    private String liens;

    private String boutons;

    private String borderBoutons;

    private String body;

    //    @NotBlank(message = "La bannière de votre site est requise")
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


  public String getBorderBoutons() {
        return borderBoutons;
    }

    public void setBorderBoutons(String borderBoutons) {
        this.borderBoutons = borderBoutons;
    }

    @Transient
    public String getPhotosImagePath() {
        if (photo == null) {
            return null;
        }

        return "/" + UPLOAD_PATH + id + "/" + photo;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
