package com.shm.common.dto.auth;


import com.shm.common.dto.base.RequestDto;

public class ConfirmOtpDto extends RequestDto {
    String userName;
    String mobile;
    String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
