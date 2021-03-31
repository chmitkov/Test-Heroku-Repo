package com.ch.demo.model.service;

import com.ch.demo.model.entity.UserRoleEntity;

import java.util.Set;

public class UserServiceModel {
    private String username;
    private String password;
    private String email;
    private String imageUrl;
    private Set<UserRoleEntity> roles;

    public UserServiceModel() {
    }

    public String getUsername() {
        return username;
    }

    public UserServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public UserServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Set<UserRoleEntity> getRoles() {
        return roles;
    }

    public UserServiceModel setRoles(Set<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }
}
