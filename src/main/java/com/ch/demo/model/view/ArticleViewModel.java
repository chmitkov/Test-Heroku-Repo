package com.ch.demo.model.view;

import com.ch.demo.model.entity.enums.Genre;
import com.ch.demo.model.service.UserServiceModel;

import java.time.LocalDateTime;

public class ArticleViewModel {

    private Long id;
    private String title;
    private String content;
    private String imageUrl;
    private LocalDateTime addedOn;
    private Genre genre;
    private String author;

    public ArticleViewModel() {
    }

    public String getTitle() {
        return title;
    }

    public ArticleViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ArticleViewModel setContent(String content) {
        this.content = content;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ArticleViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public ArticleViewModel setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
        return this;
    }

    public Genre getGenre() {
        return genre;
    }

    public ArticleViewModel setGenre(Genre genre) {
        this.genre = genre;
        return this;
    }


    public String getAuthor() {
        return author;
    }

    public ArticleViewModel setAuthor(String author) {
        this.author = author;
        return this;
    }

    public Long getId() {
        return id;
    }

    public ArticleViewModel setId(Long id) {
        this.id = id;
        return this;
    }
}
