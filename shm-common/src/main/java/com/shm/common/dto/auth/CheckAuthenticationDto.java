package com.shm.common.dto.auth;


import com.shm.common.dto.base.RequestDto;

public class CheckAuthenticationDto extends RequestDto {
    String authToken;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
