package com.ch.demo.service;

import com.ch.demo.model.entity.UserEntity;
import com.ch.demo.model.service.RegistrationServiceModel;

public interface UserService {
    void register(RegistrationServiceModel registrationServiceModel);

    UserEntity findByEmail(String name);

    boolean existsByName(String name);

    void seedUsers();

}
