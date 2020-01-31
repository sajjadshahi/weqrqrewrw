package com.shm.core.domain.repository.user;

import com.shm.core.domain.entity.user.Notification;
import com.shm.core.domain.entity.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NotificationRepository extends CrudRepository<Notification, Long> {
    List<Notification> findAll();

}
