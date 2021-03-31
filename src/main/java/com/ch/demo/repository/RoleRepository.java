package com.ch.demo.repository;

import com.ch.demo.model.entity.UserRoleEntity;
import com.ch.demo.model.entity.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<UserRoleEntity, Long> {
    Optional<UserRoleEntity> findByRole(UserRole role);
}
