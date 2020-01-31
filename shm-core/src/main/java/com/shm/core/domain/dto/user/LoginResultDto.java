package com.shm.core.domain.dto.user;

import com.shm.common.dto.base.ResultDto;

public class LoginResultDto extends ResultDto {
    private String token;

    public LoginResultDto(boolean success, String message, String token) {
        super(success, message);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
