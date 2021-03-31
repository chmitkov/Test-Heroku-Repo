package com.ch.demo.service;

import com.ch.demo.model.entity.ArtistEntity;

import java.io.IOException;
import java.util.List;

public interface ArtistService {
    void seedArtists() throws IOException;

    List<String> findAllArtistsNames();

    ArtistEntity findByName(String artist);
}
