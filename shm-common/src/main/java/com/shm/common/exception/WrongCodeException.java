package com.shm.common.exception;

public class WrongCodeException extends Exception {

    public WrongCodeException() {
        super();
    }

    public WrongCodeException(String message) {
        super(message);
    }

    public WrongCodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongCodeException(Throwable cause) {
        super(cause);
    }

    protected WrongCodeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
