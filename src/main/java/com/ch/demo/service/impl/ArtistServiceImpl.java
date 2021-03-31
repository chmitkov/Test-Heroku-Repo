package com.ch.demo.service.impl;

import com.ch.demo.model.entity.ArtistEntity;
import com.ch.demo.repository.ArtistRepository;
import com.ch.demo.service.ArtistService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;
    private final Gson gson;
    private final Resource artistsFile;

    public ArtistServiceImpl(
        ArtistRepository artistRepository,
        Gson gson,
        @Value("classpath:init/artists.json") Resource artistsFile) {
        this.artistRepository = artistRepository;
        this.gson = gson;
        this.artistsFile = artistsFile;
    }

    @Override
    public void seedArtists() throws IOException {
        if (artistRepository.count() == 0) {
            ArtistEntity[] artists = gson.fromJson(Files.readString(Path.of(artistsFile.getURI())),
                ArtistEntity[].class);
            Arrays.stream(artists).forEach(artistRepository::save);
        }
    }

    @Override
    public List<String> findAllArtistsNames() {
        return artistRepository.findAllArtistsNames();
    }

    @Override
    public ArtistEntity findByName(String artist) {
        return artistRepository
                .findByName(artist)
                .orElse(null);
    }
}
