package com.shm.common.exception.user;

public class CantReferYourselfException extends Exception {
    public CantReferYourselfException() {
        super();
    }

    public CantReferYourselfException(String message) {
        super(message);
    }

    public CantReferYourselfException(String message, Throwable cause) {
        super(message, cause);
    }

    public CantReferYourselfException(Throwable cause) {
        super(cause);
    }

    protected CantReferYourselfException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}