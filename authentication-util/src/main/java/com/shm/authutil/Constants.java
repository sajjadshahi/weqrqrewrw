package com.shm.authutil;

public class Constants {
    public static final String SHM_API_URL = "http://127.0.0.1:9091";
//    public static final String SHM_API_URL = "http://79.175.163.71:80";

    public static final String SHM_API_CHECK_URL = SHM_API_URL + "/rest/oauth/auth/check";
    public static final String SHM_API_SEND_OTP_URL = SHM_API_URL + "/rest/oauth/auth/send-otp";
    public static final String SHM_API_CREATE_TOKEN_URL = SHM_API_URL + "/rest/oauth/auth/create-token";

    public static final String ENCRYPTION_KEY = "393890313330103035383139";

}
