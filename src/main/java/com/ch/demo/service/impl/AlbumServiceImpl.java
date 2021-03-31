package com.ch.demo.service.impl;

import com.ch.demo.model.entity.AlbumEntity;
import com.ch.demo.model.service.AlbumServiceModel;
import com.ch.demo.model.view.AlbumViewModel;
import com.ch.demo.repository.AlbumRepository;
import com.ch.demo.service.AlbumService;
import com.ch.demo.service.ArtistService;
import com.ch.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final UserService userService;
    private final ArtistService artistService;
    private final ModelMapper modelMapper;

    public AlbumServiceImpl(AlbumRepository albumRepository, UserService userService, ArtistService artistService, ModelMapper modelMapper) {
        this.albumRepository = albumRepository;
        this.userService = userService;
        this.artistService = artistService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addAlbum(AlbumServiceModel albumServiceModel, String username) {
        AlbumEntity album = modelMapper.map(albumServiceModel, AlbumEntity.class);
        album.setAddedFrom(userService.findByEmail(username));
        album.setArtist(artistService.findByName(albumServiceModel.getArtist()));

        albumRepository.save(album);
    }

    @Override
    public List<AlbumEntity> findAll() {
        return albumRepository
                .findAll();
    }

    @Override
    public AlbumViewModel finById(Long id) {
        AlbumEntity album = albumRepository
                .findById(id)
                .orElse(null);

        AlbumViewModel albumView = modelMapper.map(album, AlbumViewModel.class);
        albumView.setArtist(album.getArtist().getName());
        albumView.setVideoUrl(album.getVideoUrl()
                .substring(album.getVideoUrl().indexOf('=') + 1));
        return albumView;
    }

    @Override
    public AlbumEntity findById(Long albumId) {
        return albumRepository
                .findById(albumId)
                .orElse(null);
    }

    @Override
    public int findAlbumsCount() {
        return (int) albumRepository.count();
    }
}
