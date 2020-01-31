package com.shm.oauth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.shm.oauth.exception.AuthenticationFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtAuthenticationManager implements AuthenticationManager {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationManager.class);

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Override
    public String authenticate(String userName) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            String token = JWT.create()
                    .withClaim("id", userName)
                    .withClaim("exp", new Date().getTime() + 2 * 365 * 24 * 60 * 60 * 1000l) // 2 years
                    .withClaim("iat", new Date().getTime())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            logger.error("Error in creating token.", exception);

            return null;
        }
    }

    @Override
    public String isAuthenticated(String token) throws AuthenticationFailedException {
        try {
            DecodedJWT jwt = JWT.decode(token);
            Long expirationTime = Long.parseLong(jwt.getClaim("exp").as(Object.class).toString());
            if (expirationTime * 1000 - new Date().getTime() < 0)
                throw new AuthenticationFailedException();

            return jwt.getClaim("id").as(Object.class).toString();
        } catch (JWTDecodeException exception) {
            throw new AuthenticationFailedException(String.format("token : %s", token));
        }
    }
}
