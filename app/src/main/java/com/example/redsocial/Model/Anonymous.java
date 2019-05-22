package com.example.redsocial.Model;

public class Anonymous {
    private String anonymousID;
    private String Pin;
    private String level;
    private String description;
    private String Image;


    private Anonymous(){

    }

    public Anonymous(String anonymousID,String pin,String level,String description,String Image){
        this.anonymousID = anonymousID;
        Pin = pin;
        this.level = level;
        this.description = description;
        this.Image=Image;
    }

    public String getAnonymousID() {
        return anonymousID;
    }

    public void setAnonymousID(String anonymousID) {
        this.anonymousID = anonymousID;
    }

    public String getPin() {
        return Pin;
    }

    public void setPin(String pin) {
        Pin = pin;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
