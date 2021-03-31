package com.ch.demo.model.service;

import java.time.LocalDateTime;

public class CommentServiceModel extends BaseServiceModel{

    private String text;
    private LocalDateTime addedOn;
    private AlbumServiceModel album;
    private UserServiceModel user;

    public CommentServiceModel() {
    }

    public String getText() {
        return text;
    }

    public CommentServiceModel setText(String text) {
        this.text = text;
        return this;
    }

    public AlbumServiceModel getAlbum() {
        return album;
    }

    public CommentServiceModel setAlbum(AlbumServiceModel album) {
        this.album = album;
        return this;
    }

    public UserServiceModel getUser() {
        return user;
    }

    public CommentServiceModel setUser(UserServiceModel user) {
        this.user = user;
        return this;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public CommentServiceModel setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
        return this;
    }
}
