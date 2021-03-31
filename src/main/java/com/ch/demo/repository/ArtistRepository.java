package com.ch.demo.repository;

import com.ch.demo.model.entity.ArtistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<ArtistEntity, Long> {

    @Query("SELECT a.name FROM ArtistEntity a " +
            "ORDER BY a.name" )
    List<String> findAllArtistsNames();

    Optional<ArtistEntity> findByName(String name);
}
