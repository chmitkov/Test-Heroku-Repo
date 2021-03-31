package com.ch.demo.service.impl;

import com.ch.demo.model.entity.LogEntity;
import com.ch.demo.model.entity.enums.LogType;
import com.ch.demo.repository.LogRepository;
import com.ch.demo.service.LogService;
import com.ch.demo.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;
    private final UserService userService;


    public LogServiceImpl(LogRepository logRepository, UserService userService) {
        this.logRepository = logRepository;
        this.userService = userService;
    }


    @Override
    public void addLog(LogType logType) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        String email = userDetails.getUsername();

        LogEntity log = new LogEntity()
                .setType(logType)
                .setDateTime(LocalDateTime.now())
                .setUser(userService.findByEmail(email));

        logRepository.save(log);
    }
}
