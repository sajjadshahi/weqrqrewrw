package com.shm.common.exception;

public class RedisConnectionException extends Exception {

    public RedisConnectionException() {
        super();
    }

    public RedisConnectionException(String message) {
        super(message);
    }

    public RedisConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public RedisConnectionException(Throwable cause) {
        super(cause);
    }

    protected RedisConnectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
