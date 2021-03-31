package com.ch.demo.web;

import com.ch.demo.init.DatabaseInitializer;
import com.ch.demo.model.entity.enums.Genre;
import com.ch.demo.model.service.AlbumServiceModel;
import com.ch.demo.service.AlbumService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class AlbumControllerTest {

    private static final String ALBUM_CONTROLLER_PREFIX = "/albums";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private DatabaseInitializer databaseInitializer;
    @Autowired
    private AlbumService albumService;

    @BeforeEach
    void setUp() throws Exception {
        databaseInitializer.run();
        AlbumServiceModel albumServiceModel = new AlbumServiceModel(1L)
                .setName("Valid album")
                .setImageUrl("ValidUrl")
                .setArtist("Queen")
                .setCopies(1234)
                .setPrice(BigDecimal.TEN)
                .setGenre(Genre.METAL)
                .setVideoUrl("ValidVideoUrl");
        albumService.addAlbum(albumServiceModel, "admin");
    }

    @Test
    @WithMockUser(value = "gosho", roles = {"USER", "ADMIN"})
    void detailsShouldReturnValidStatusViewNameAndModel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ALBUM_CONTROLLER_PREFIX + "/details/{id}",
                1))
                .andExpect(status().isOk())
                .andExpect(view().name("details"))
                .andExpect(model().attributeExists("album"));
    }

    @Test
    @WithMockUser(value = "gosho", roles = {"USER", "ADMIN"})
    void detailsShouldReturnValidModelAttributes() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ALBUM_CONTROLLER_PREFIX + "/details/{id}",
                1))
                .andExpect(status().isOk())
                .andExpect(view().name("details"))
                .andExpect(model().attributeExists("album"));
    }

    @Test
    @WithAnonymousUser
    void detailsWithAnonymousUserShouldThrowAccessDeniedException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ALBUM_CONTROLLER_PREFIX + "/details/{id}",
                4))
                .andExpect(mvcResult -> assertTrue(mvcResult
                        .getResolvedException() instanceof AccessDeniedException));
    }

    @Test
    @WithMockUser(value = "gosho", roles = {"USER", "ADMIN"})
    void detailsWithInvalidIdShouldReturnNotFoundAlbum() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ALBUM_CONTROLLER_PREFIX + "/details/{id}",
                999999999))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof IllegalArgumentException));

    }

    @Test
    @WithMockUser(value = "gosho", roles = {"USER", "ADMIN"})
    void addAlbumGetRequestShouldReturnCorrectStatusViewNameAndModel() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get(ALBUM_CONTROLLER_PREFIX + "/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-album"))
                .andExpect(model().attributeExists("artists"));
    }

    @Test
    @WithAnonymousUser
    void addAlbumWithAnonymousUser() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get(ALBUM_CONTROLLER_PREFIX + "/add"))
                .andExpect(mvcResult -> {
                    assertTrue(mvcResult.getResolvedException() instanceof AccessDeniedException);
                });
    }


    @Test
    @WithMockUser(username = "gosho", roles = {"USER", "ADMIN"})
    void addAlbumPostRequest() throws Exception {

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(ALBUM_CONTROLLER_PREFIX + "/add")
                        .param("name", "testName")
                        .param("imageUrl", "imageUrl")
                        .param("videoUrl", "https://www.youtube.com/watch?v=kk6wLZN4X74")
                        .param("price", "10")
                        .param("copies", "22")
                        .param("releaseDate", "2000-01-01")
                        .param("description", "Description")
                        .param("artist", "Metallica")
                        .param("genre", "POP")
                        .with(csrf())
                );


        assertEquals(2, albumService.findAlbumsCount());
        assertEquals("testName", albumService.finById(2L).getName());
        assertEquals("imageUrl", albumService.finById(2L).getImageUrl());


    }

    @Test
    @WithMockUser(username = "gosho", roles = {"USER", "ADMIN"})
    void addAlbumPostRequestWithInvalidParameters() throws Exception {

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(ALBUM_CONTROLLER_PREFIX + "/add")
                        .param("name", "te")
                        .param("imageUrl", "ima")
                        .param("videoUrl", "kk6wLZN4X74")
                        .param("price", "-10")
                        .param("copies", "-22")
                        .param("releaseDate", "2000-01-01")
                        .param("description", "Des")
                        .param("artist", "")
                        .param("genre", "")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection());

    }

}