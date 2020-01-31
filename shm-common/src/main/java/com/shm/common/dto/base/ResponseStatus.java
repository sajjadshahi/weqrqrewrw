package com.shm.common.dto.base;

public enum ResponseStatus {
    SUCCESS(200),
    APPLICATION_NOT_FOUNDED(300),
    USER_NOT_FOUNDED(302),
    INTERNAL_SERVER_ERROR(503),
    ;

    int value;

    ResponseStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
