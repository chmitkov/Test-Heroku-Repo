package com.ch.demo.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    private String username;
    private String password;
    private String email;
    private String imageUrl;
    private Set<UserRoleEntity> roles;

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    @Column(name = "email", unique = true)
    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    @Column(name = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    public UserEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public UserEntity setRoles(Set<UserRoleEntity> authorities) {
        this.roles = authorities;
        return this;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public Set<UserRoleEntity> getRoles() {
        return this.roles;
    }

    @Column(name = "password")
    public String getPassword() {
        return this.password;
    }

    @Column(name = "username", unique = true)
    public String getUsername() {
        return this.username;
    }

}
