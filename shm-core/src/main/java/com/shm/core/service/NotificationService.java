package com.shm.core.service;

import com.shm.core.domain.dto.user.NotificationDto;
import com.shm.core.domain.dto.user.UserDto;
import com.shm.core.domain.entity.user.Notification;
import com.shm.core.domain.repository.user.LogRepository;
import com.shm.core.domain.repository.user.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    LogService logService;

    public List<NotificationDto> getNotificationsList() {
        ArrayList<NotificationDto> dtos = new ArrayList<>();

        notificationRepository.findAll().forEach(notification -> {
            NotificationDto dto = new NotificationDto();
            dto.setDescription(notification.getDescription());
            dto.setTitle(notification.getTitle());
            dto.setMedium(notification.getMedium());
            dto.setPriority(notification.getPriority());
            dto.setSeverity(notification.getSeverity());
            dto.setId(notification.getId());

            dtos.add(dto);
        });

        return dtos;
    }

    public String create(String title, String description, int priority, int severity, int media) {
        Notification notification = new Notification();

        notification.setDescription(description);
        notification.setTitle(title);
        notification.setMedium(media);
        notification.setPriority(priority);
        notification.setSeverity(severity);
        notificationRepository.save(notification);

        return "Notification Created";
    }

    public String performNotification(long notificationId) {
        try {
            Notification notification = notificationRepository.findById(notificationId).get();
            String callLog = notification.distribute();
            logService.addLog(callLog);
            return callLog;
        } catch (Exception e) {
            return "Not Found";
        }

    }

    public String cancelNotification(Long notificationId) {
        try {
            Notification notification = notificationRepository.findById(notificationId).get();
            String callLog = notification.cancel();
            logService.addLog(callLog);
            return callLog;
        } catch (Exception e) {
            return "Not Found";
        }
    }
}
