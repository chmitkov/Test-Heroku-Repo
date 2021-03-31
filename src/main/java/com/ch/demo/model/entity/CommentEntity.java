package com.ch.demo.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class CommentEntity extends BaseEntity {

    private String text;
    private LocalDateTime addedOn;
    private AlbumEntity album;
    private UserEntity user;

    public CommentEntity() {
    }

    @Column(name = "text", nullable = false, columnDefinition = "TEXT")
    public String getText() {
        return text;
    }

    public CommentEntity setText(String text) {
        this.text = text;
        return this;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public AlbumEntity getAlbum() {
        return album;
    }

    public CommentEntity setAlbum(AlbumEntity album) {
        this.album = album;
        return this;
    }

    @ManyToOne
    public UserEntity getUser() {
        return user;
    }

    public CommentEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    @Column(name = "added_on", nullable = false)
    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public CommentEntity setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
        return this;
    }
}
