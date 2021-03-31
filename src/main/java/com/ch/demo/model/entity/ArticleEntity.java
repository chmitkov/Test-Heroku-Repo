package com.ch.demo.model.entity;

import com.ch.demo.model.entity.enums.Genre;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "articles")
public class ArticleEntity extends BaseEntity{

    private String title;
    private String content;
    private String imageUrl;
    private LocalDateTime addedOn;
    private Genre genre;
    private UserEntity author;

    public ArticleEntity() {
    }

    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public ArticleEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    public String getContent() {
        return content;
    }

    public ArticleEntity setContent(String content) {
        this.content = content;
        return this;
    }

    @Column(name = "image_url", nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    public ArticleEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    @Column(name = "added_on", nullable = false)
    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public ArticleEntity setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
        return this;
    }

    @Enumerated(EnumType.STRING)
    public Genre getGenre() {
        return genre;
    }

    public ArticleEntity setGenre(Genre genre) {
        this.genre = genre;
        return this;
    }

    @ManyToOne
    public UserEntity getAuthor() {
        return author;
    }

    public ArticleEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }
}
