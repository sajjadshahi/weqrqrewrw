package com.shm.common.dto.base;

public class ResultDto {
    boolean success;
    String message;
    ResultErrorCode errorCode;
    String error;

    public ResultDto() {
    }

    public ResultDto(boolean success, String message, ResultErrorCode errorCode, String error) {
        this.success = success;
        this.message = message;
        this.errorCode = errorCode;
        this.error = error;
    }

    public ResultDto(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ResultErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
