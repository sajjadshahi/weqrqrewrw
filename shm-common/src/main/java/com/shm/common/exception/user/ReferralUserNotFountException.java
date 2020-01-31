package com.shm.common.exception.user;

public class ReferralUserNotFountException extends Exception {
    public ReferralUserNotFountException() {
        super();
    }

    public ReferralUserNotFountException(String message) {
        super(message);
    }

    public ReferralUserNotFountException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReferralUserNotFountException(Throwable cause) {
        super(cause);
    }

    protected ReferralUserNotFountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}