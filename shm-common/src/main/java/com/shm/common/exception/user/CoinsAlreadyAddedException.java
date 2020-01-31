package com.shm.common.exception.user;

public class CoinsAlreadyAddedException extends Exception {
    public CoinsAlreadyAddedException() {
        super();
    }

    public CoinsAlreadyAddedException(String message) {
        super(message);
    }

    public CoinsAlreadyAddedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CoinsAlreadyAddedException(Throwable cause) {
        super(cause);
    }

    protected CoinsAlreadyAddedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}