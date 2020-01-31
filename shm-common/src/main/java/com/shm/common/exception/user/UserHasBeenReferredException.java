package com.shm.common.exception.user;

public class UserHasBeenReferredException extends Exception {
    public UserHasBeenReferredException() {
        super();
    }

    public UserHasBeenReferredException(String message) {
        super(message);
    }

    public UserHasBeenReferredException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserHasBeenReferredException(Throwable cause) {
        super(cause);
    }

    protected UserHasBeenReferredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}