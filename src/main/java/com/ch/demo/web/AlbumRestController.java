package com.ch.demo.web;

import com.ch.demo.model.entity.AlbumEntity;
import com.ch.demo.service.AlbumService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/albums/api")
public class AlbumRestController {

    private final AlbumService albumService;

    public AlbumRestController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @CrossOrigin("*")
    @GetMapping()
    public ResponseEntity<List<AlbumEntity>> findAll() {
        return ResponseEntity
                .ok()
                .body(albumService.findAll());
    }
}
