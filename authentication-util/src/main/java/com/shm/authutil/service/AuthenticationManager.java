package com.shm.authutil.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.shm.authutil.exception.AuthenticationFailedException;
import com.shm.common.dto.base.ResultDto;
import com.shm.common.dto.base.ResultErrorCode;
import com.shm.common.exception.InvalidPhoneNumberException;
import com.shm.common.exception.WrongCodeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthenticationManager {
    private Logger logger = LoggerFactory.getLogger(AuthenticationManager.class);

    public String isAuthenticated(String token) throws AuthenticationFailedException {
        if (token == null || token.equals("")) {
            throw new AuthenticationFailedException(String.format("token : %s", token));
        }
        try {
            DecodedJWT jwt = JWT.decode(token);
            Long expirationTime = Long.parseLong(jwt.getClaim("exp").as(Object.class).toString());
            if (expirationTime * 1000 - new Date().getTime() < 0)
                throw new AuthenticationFailedException();

            return jwt.getClaim("id").as(Object.class).toString();
        } catch (JWTDecodeException ex) {
            logger.error(String.format("jwt cannot decode token : %s. %s", token, ex.getMessage()));
            throw new AuthenticationFailedException(String.format("token : %s", token));
        }
    }

    public String createToken(String mobile, String password, String userName) throws InvalidPhoneNumberException, WrongCodeException {
        ResultDto resultDto = SHMAuthenticationService.createToken(mobile, password, userName);

        if (resultDto != null && resultDto.isSuccess())
            return resultDto.getMessage();
        else {
            if (resultDto != null) {
                if (resultDto.getErrorCode().equals(ResultErrorCode.INVALID_PHONE))
                    throw new InvalidPhoneNumberException();
            }

            throw new WrongCodeException();
        }
    }

}
