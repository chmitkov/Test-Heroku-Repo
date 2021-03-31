package com.ch.demo.model.entity;

import com.ch.demo.model.entity.enums.UserRole;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class UserRoleEntity extends BaseEntity {

    private UserRole role;

    public UserRoleEntity() {
    }

    public UserRoleEntity(UserRole role) {
        this.role = role;
    }

    @Enumerated(EnumType.STRING)
    public UserRole getRole() {
        return this.role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
