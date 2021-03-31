package com.ch.demo.model.entity;

import com.ch.demo.model.entity.enums.LogType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "logs")
public class LogEntity extends BaseEntity{

    private LogType type;
    private LocalDateTime dateTime;
    private UserEntity user;

    public LogEntity() {
    }

    @Enumerated(EnumType.STRING)
    public LogType getType() {
        return type;
    }

    public LogEntity setType(LogType type) {
        this.type = type;
        return this;
    }


    @Column(name = "date_time", nullable = false)
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public LogEntity setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    @ManyToOne
    public UserEntity getUser() {
        return user;
    }

    public LogEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }
}
