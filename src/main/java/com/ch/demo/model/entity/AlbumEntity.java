package com.ch.demo.model.entity;

import com.ch.demo.model.entity.enums.Genre;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "albums")
public class AlbumEntity extends BaseEntity {

    private String name;
    private String imageUrl;
    private String videoUrl;
    private String description;
    private Integer copies;
    private BigDecimal price;
    private LocalDate releaseDate;
    private Genre genre;
    private ArtistEntity artist;
    private UserEntity addedFrom;

    public AlbumEntity() {
    }

    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public AlbumEntity setName(String name) {
        this.name = name;
        return this;
    }

    @Column(name = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    public AlbumEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    @Column(name = "description", columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public AlbumEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    @Column(name = "copies")
    public Integer getCopies() {
        return copies;
    }

    public AlbumEntity setCopies(Integer copies) {
        this.copies = copies;
        return this;
    }

    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public AlbumEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @Column(name = "release_date")
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public AlbumEntity setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }


    @Enumerated(EnumType.STRING)
    public Genre getGenre() {
        return genre;
    }

    public AlbumEntity setGenre(Genre genre) {
        this.genre = genre;
        return this;
    }

    @ManyToOne
    public ArtistEntity getArtist() {
        return artist;
    }

    public AlbumEntity setArtist(ArtistEntity artist) {
        this.artist = artist;
        return this;
    }

    @ManyToOne
    public UserEntity getAddedFrom() {
        return addedFrom;
    }

    public AlbumEntity setAddedFrom(UserEntity addedFrom) {
        this.addedFrom = addedFrom;
        return this;
    }

    @Column(name = "video_url", nullable = false)
    public String getVideoUrl() {
        return videoUrl;
    }

    public AlbumEntity setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }
}

