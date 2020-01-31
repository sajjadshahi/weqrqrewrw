package com.shm.common.exception.user;

public class IncorrectCodeException extends Exception {
    public IncorrectCodeException() {
        super();
    }

    public IncorrectCodeException(String message) {
        super(message);
    }

    public IncorrectCodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectCodeException(Throwable cause) {
        super(cause);
    }

    protected IncorrectCodeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}