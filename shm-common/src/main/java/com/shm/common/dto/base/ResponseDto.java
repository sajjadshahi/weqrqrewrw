package com.shm.common.dto.base;

public class ResponseDto<T> {

    private T entity;
    private boolean success;
    private String errorMessage;

    public ResponseDto() {
    }

    public ResponseDto(T entity, boolean success) {
        this.entity = entity;
        this.success = success;
    }

    public ResponseDto(T entity, boolean success, String errorMessage) {
        this.entity = entity;
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public ResponseDto(T entity) {
        this.entity = entity;
        this.success = true;
        this.errorMessage = null;
    }

    public ResponseDto(boolean success, String errorMessage) {
        this.entity = null;
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

