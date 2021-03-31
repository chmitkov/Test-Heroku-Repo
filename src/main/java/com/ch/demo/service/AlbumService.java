package com.ch.demo.service;

import com.ch.demo.model.entity.AlbumEntity;
import com.ch.demo.model.service.AlbumServiceModel;
import com.ch.demo.model.view.AlbumViewModel;

import java.util.List;

public interface AlbumService {
    void addAlbum(AlbumServiceModel albumServiceModel, String name);

    List<AlbumEntity> findAll();

    AlbumViewModel finById(Long id);

    AlbumEntity findById(Long albumId);

    int findAlbumsCount();


}
