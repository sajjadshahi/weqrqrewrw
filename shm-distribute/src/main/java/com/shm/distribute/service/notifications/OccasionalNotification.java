package com.shm.distribute.service.notifications;

import com.shm.core.domain.entity.user.User;

import java.util.Date;

public class OccasionalNotification extends Notification {
    private Date fromDate;
    private Date toDate;
    private final String typeString = "Occasional";
    private final int type = 3;
}
