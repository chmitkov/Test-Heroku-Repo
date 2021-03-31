package com.ch.demo.model.entity;

import com.google.gson.annotations.Expose;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "artists")
public class ArtistEntity extends BaseEntity{

    @Expose
    private String name;
    @Expose
    private String careerInformation;

    public ArtistEntity() {
    }

    @Column(name = "name", unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public ArtistEntity setName(String name) {
        this.name = name;
        return this;
    }

    @Column(name = "career_information", columnDefinition = "TEXT")
    public String getCareerInformation() {
        return careerInformation;
    }

    public ArtistEntity setCareerInformation(String careerInformation) {
        this.careerInformation = careerInformation;
        return this;
    }
}
