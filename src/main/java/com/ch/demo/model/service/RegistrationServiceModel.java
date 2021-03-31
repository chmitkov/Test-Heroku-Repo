package com.ch.demo.model.service;

public class RegistrationServiceModel {

    private String username;
    private String password;
    private String email;
    private String imageUrl;

    public String getUsername() {
        return username;
    }

    public RegistrationServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegistrationServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RegistrationServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public RegistrationServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
