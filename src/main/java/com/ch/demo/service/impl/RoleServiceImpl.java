package com.ch.demo.service.impl;

import com.ch.demo.model.entity.UserRoleEntity;
import com.ch.demo.model.entity.enums.UserRole;
import com.ch.demo.repository.RoleRepository;
import com.ch.demo.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void seedRoles() {
        if (roleRepository.count() == 0) {
            UserRoleEntity admin = new UserRoleEntity(UserRole.ADMIN);
            UserRoleEntity user = new UserRoleEntity(UserRole.USER);

            roleRepository.saveAll(List.of(admin, user));
        }
    }
}
