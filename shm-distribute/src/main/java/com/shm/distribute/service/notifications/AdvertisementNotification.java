package com.shm.distribute.service.notifications;

import java.util.Date;
import com.shm.core.domain.entity.user.User;
public class AdvertisementNotification extends Notification {

        private Date date;
        private boolean confirmed = false;
        private User Publisher;
        private final String typeString = "ADVERTISEMENT";
        private final int type = 2;
    }
