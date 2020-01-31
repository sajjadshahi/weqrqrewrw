package com.shm.core.domain.dto.user;

import com.shm.common.dto.base.ResultDto;


public class NotificationDto {

    private Long id;

    private String title;

    private String description;

    private int severity;

    private int medium;

    private int priority;

    public NotificationDto() {
    }

    public NotificationDto(Long id, String title, String description, int severity, int medium, int priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.severity = severity;
        this.medium = medium;
        this.priority = priority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public int getMedium() {
        return medium;
    }

    public void setMedium(int medium) {
        this.medium = medium;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
