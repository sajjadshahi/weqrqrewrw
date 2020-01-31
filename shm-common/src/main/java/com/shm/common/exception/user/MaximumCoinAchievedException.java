package com.shm.common.exception.user;

public class MaximumCoinAchievedException extends Exception {
    public MaximumCoinAchievedException() {
        super();
    }

    public MaximumCoinAchievedException(String message) {
        super(message);
    }

    public MaximumCoinAchievedException(String message, Throwable cause) {
        super(message, cause);
    }

    public MaximumCoinAchievedException(Throwable cause) {
        super(cause);
    }

    protected MaximumCoinAchievedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}