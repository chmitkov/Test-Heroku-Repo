package com.ch.demo.init;

import com.ch.demo.service.ArtistService;
import com.ch.demo.service.RoleService;
import com.ch.demo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final RoleService roleService;
    private final UserService userService;
    private final ArtistService artistService;

    public DatabaseInitializer(RoleService roleService,
        UserService userService,
        ArtistService artistService) {
        this.roleService = roleService;
        this.userService = userService;
        this.artistService = artistService;
    }

    @Override
    public void run(String... args) throws Exception {
        //ToDo Change this with Spring Event
        roleService.seedRoles();
        artistService.seedArtists();
        userService.seedUsers();
    }
}
