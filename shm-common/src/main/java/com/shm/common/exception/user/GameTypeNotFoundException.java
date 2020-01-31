package com.shm.common.exception.user;

public class GameTypeNotFoundException extends Exception {
    public GameTypeNotFoundException() {
        super();
    }

    public GameTypeNotFoundException(String message) {
        super(message);
    }

    public GameTypeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameTypeNotFoundException(Throwable cause) {
        super(cause);
    }

    protected GameTypeNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}