package com.shm.common.exception.user;

public class NoActiveCampaignException extends Exception {
    public NoActiveCampaignException() {
        super();
    }

    public NoActiveCampaignException(String message) {
        super(message);
    }

    public NoActiveCampaignException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoActiveCampaignException(Throwable cause) {
        super(cause);
    }

    protected NoActiveCampaignException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}