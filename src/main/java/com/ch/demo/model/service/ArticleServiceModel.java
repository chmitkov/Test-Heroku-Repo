package com.ch.demo.model.service;

import com.ch.demo.model.entity.enums.Genre;

import java.time.LocalDateTime;

public class ArticleServiceModel extends BaseServiceModel {

    private String title;
    private String content;
    private String imageUrl;
    private LocalDateTime addedOn;
    private Genre genre;
    private UserServiceModel author;

    public ArticleServiceModel() {
    }

    public String getTitle() {
        return title;
    }

    public ArticleServiceModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ArticleServiceModel setContent(String content) {
        this.content = content;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ArticleServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public ArticleServiceModel setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
        return this;
    }

    public Genre getGenre() {
        return genre;
    }

    public ArticleServiceModel setGenre(Genre genre) {
        this.genre = genre;
        return this;
    }

    public UserServiceModel getAuthor() {
        return author;
    }

    public ArticleServiceModel setAuthor(UserServiceModel author) {
        this.author = author;
        return this;
    }
}
