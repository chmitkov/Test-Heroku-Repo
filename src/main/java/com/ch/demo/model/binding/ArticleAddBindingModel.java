package com.ch.demo.model.binding;

import com.ch.demo.model.entity.enums.Genre;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ArticleAddBindingModel {

    private String title;
    private String content;
    private String imageUrl;
    private Genre genre;

    public ArticleAddBindingModel() {
    }

    @Size(min = 2, message = "Title must be more than 2 characters")
    public String getTitle() {
        return title;
    }

    public ArticleAddBindingModel setTitle(String title) {
        this.title = title;
        return this;
    }

    @Size(min = 5, message = "Content must be more than 5 characters")
    public String getContent() {
        return content;
    }

    public ArticleAddBindingModel setContent(String content) {
        this.content = content;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ArticleAddBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    @NotNull(message = "You must select the genre")
    public Genre getGenre() {
        return genre;
    }

    public ArticleAddBindingModel setGenre(Genre genre) {
        this.genre = genre;
        return this;
    }
}
