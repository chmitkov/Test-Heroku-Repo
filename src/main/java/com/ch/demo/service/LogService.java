package com.ch.demo.service;

import com.ch.demo.model.entity.enums.LogType;

public interface LogService {
    void addLog(LogType login);
}
