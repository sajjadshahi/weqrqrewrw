package com.shm.oauth.factory.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NotificationFactory {
    @Autowired
    JwtAuthenticationManager jwtAuthenticationManager;

    @Value("${authentication.method}")
    private String authMethod;

    public AuthenticationManager getAuthenticationMethod(boolean isMock) {
        AuthenticationMethod method = AuthenticationMethod.valueOf(authMethod);

        switch (method) {
            case JWT:
                return jwtAuthenticationManager;
                // OTHER THINGS CAN BE ADDED
        }

        return null;
    }
}
