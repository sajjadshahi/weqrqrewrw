package com.shm.oauth.service;

import com.shm.oauth.exception.AuthenticationFailedException;

public interface AuthenticationManager {
    String authenticate(String userName);

    String isAuthenticated(String token) throws AuthenticationFailedException;
}
