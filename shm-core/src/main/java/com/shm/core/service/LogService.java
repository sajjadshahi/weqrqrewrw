package com.shm.core.service;

import com.shm.core.domain.dto.user.LogDto;
import com.shm.core.domain.dto.user.NotificationDto;
import com.shm.core.domain.entity.user.Log;
import com.shm.core.domain.repository.user.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogService {

    @Autowired
    LogRepository logRepository;

    public void addLog(String text){
        Log log = new Log(text);
        logRepository.save(log);
    }

    public List<LogDto> getLogsList() {
        ArrayList<LogDto> dtos = new ArrayList<>();

        logRepository.findAll().forEach(log -> {
            LogDto dto = new LogDto();
            dto.setText(log.getText());
            dto.setId(log.getId());

            dtos.add(dto);
        });

        return dtos;
    }
}
