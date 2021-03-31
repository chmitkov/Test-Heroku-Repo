package com.ch.demo.model.binding;

import com.ch.demo.model.entity.enums.Genre;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AlbumAddBindingModel {

    private String name;
    private String imageUrl;
    private String videoUrl;
    private BigDecimal price;
    private Integer copies;
    private LocalDate releaseDate;
    private String description;
    private String artist;
    private Genre genre;

    public AlbumAddBindingModel() {
    }

    @Size(min = 3, message = "Name must be more than 3 characters")
    public String getName() {
        return name;
    }

    public AlbumAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    @Size(min = 3, message = "Image url must be more than 3 characters")
    public String getImageUrl() {
        return imageUrl;
    }

    public AlbumAddBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    @PositiveOrZero(message = "Price must be positive")
    public BigDecimal getPrice() {
        return price;
    }

    public AlbumAddBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @PositiveOrZero(message = "Copies must be positive")
    public Integer getCopies() {
        return copies;
    }

    public AlbumAddBindingModel setCopies(Integer copies) {
        this.copies = copies;
        return this;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "The date cannot be in the future")
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public AlbumAddBindingModel setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    @NotBlank(message = "You must select the artist")
    public String getArtist() {
        return artist;
    }

    public AlbumAddBindingModel setArtist(String artist) {
        this.artist = artist;
        return this;
    }
    @NotNull(message = "You must select the genre")
    public Genre getGenre() {
        return genre;
    }

    public AlbumAddBindingModel setGenre(Genre genre) {
        this.genre = genre;
        return this;
    }




    @Size(min = 5, message = "The description must be more than five characters")
    public String getDescription() {
        return description;
    }

    public AlbumAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    @Pattern(regexp = "https:\\/\\/www\\.youtube\\.com.+",
    message = "Enter valid youtube address")
    public String getVideoUrl() {
        return videoUrl;
    }

    public AlbumAddBindingModel setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }
}
