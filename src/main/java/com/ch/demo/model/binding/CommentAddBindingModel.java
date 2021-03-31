package com.ch.demo.model.binding;

public class CommentAddBindingModel {

    private String text;
    private Long albumId;
    private String username;

    public CommentAddBindingModel() {
    }

    public String getText() {
        return text;
    }

    public CommentAddBindingModel setText(String text) {
        this.text = text;
        return this;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public CommentAddBindingModel setAlbumId(Long albumId) {
        this.albumId = albumId;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public CommentAddBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }
}
