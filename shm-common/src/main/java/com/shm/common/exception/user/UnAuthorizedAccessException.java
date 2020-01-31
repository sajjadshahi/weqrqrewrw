package com.shm.common.exception.user;

public class UnAuthorizedAccessException extends Exception {
    public UnAuthorizedAccessException() {
        super();
    }

    public UnAuthorizedAccessException(String message) {
        super(message);
    }

    public UnAuthorizedAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnAuthorizedAccessException(Throwable cause) {
        super(cause);
    }

    protected UnAuthorizedAccessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}