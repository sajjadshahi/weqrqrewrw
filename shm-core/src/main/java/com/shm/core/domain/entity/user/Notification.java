package com.shm.core.domain.entity.user;

import com.shm.common.dto.base.ResultDto;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;

@Entity
@Table(name = "notification")
@EnableAutoConfiguration
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "severity")
    private int severity;

    @Column(name = "medium")
    private int medium;

    @Column(name = "priority")
    private int priority;

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

    public String distribute() {
        String log = "Notification ---" + this.title + "--- of Priority: " + this.priority + " Distribute Call!";
        System.out.println(log);
        return log;

    }

    public String cancel() {
        String log = "Notification ---" + this.title + "--- of Priority: " + this.priority + " Cancel Call!";
        System.out.println(log);
        return log;
    }

}
