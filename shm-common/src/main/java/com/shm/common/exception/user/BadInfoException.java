package com.shm.common.exception.user;

public class BadInfoException extends Exception {
    public BadInfoException() {
        super();
    }

    public BadInfoException(String message) {
        super(message);
    }

    public BadInfoException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadInfoException(Throwable cause) {
        super(cause);
    }

    protected BadInfoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String getMessage() {
        return "Bad info";
    }
}