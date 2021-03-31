package com.ch.demo.service.impl;

import com.ch.demo.model.entity.UserEntity;
import com.ch.demo.model.entity.enums.UserRole;
import com.ch.demo.model.service.RegistrationServiceModel;
import com.ch.demo.repository.RoleRepository;
import com.ch.demo.repository.UserRepository;
import com.ch.demo.service.UserService;
import java.util.List;
import java.util.Set;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UserServiceImpl(
        UserDetailsService userDetailsService,
        UserRepository userRepository,
        RoleRepository roleRepository,
        PasswordEncoder passwordEncoder,
        ModelMapper modelMapper) {
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean existsByName(String name) {
        return userRepository.existsByUsername(name);
    }

    @Override
    public void register(RegistrationServiceModel registrationServiceModel) {

        // map to entity
        UserEntity userEntity = modelMapper
                .map(registrationServiceModel, UserEntity.class);

        // encode pwd
        userEntity.setPassword(passwordEncoder.encode(registrationServiceModel.getPassword()));
        // set the roles of the user
        userEntity.setRoles(
            Set.of(roleRepository.findByRole(UserRole.USER).
                orElseThrow(
            () -> new IllegalStateException("Please init the user role!"))));
        // create the new user
        userEntity = userRepository.save(userEntity);

        // map to spring context and initialize authentication
        UserDetails userDetails = userDetailsService.loadUserByUsername(userEntity.getUsername());

        Authentication authentication = new
            UsernamePasswordAuthenticationToken(
            userDetails,
            userEntity.getPassword(),
            userDetails.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .orElse(null);

    }

    @Override
    public void seedUsers() {
        if (userRepository.count() == 0) {
            UserEntity admin = new UserEntity().
                setUsername("admin").
                setPassword(passwordEncoder.encode("topsecret")).
                setEmail("admin@example.com").
                setImageUrl("https://bit.ly/2Z67GWD");

            //todo: roles

            UserEntity normalUser = new UserEntity().
                setUsername("user").
                setPassword(passwordEncoder.encode("topsecret")).
                setEmail("user@example.com").
                setImageUrl("https://bit.ly/3pg7jmF");

            UserEntity gosho = new UserEntity().
                    setUsername("gosho").
                    setPassword(passwordEncoder.encode("123")).
                    setEmail("gosho@example.com").
                    setImageUrl("https://bit.ly/3pg7jmF");

            userRepository.saveAll(List.of(admin, normalUser, gosho));
        }
    }

}
