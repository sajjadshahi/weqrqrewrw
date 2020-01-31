package com.shm.common.exception.user;

public class PendingRequestAlreadyExistException extends Exception {
    public PendingRequestAlreadyExistException() {
        super();
    }

    public PendingRequestAlreadyExistException(String message) {
        super(message);
    }

    public PendingRequestAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public PendingRequestAlreadyExistException(Throwable cause) {
        super(cause);
    }

    protected PendingRequestAlreadyExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}