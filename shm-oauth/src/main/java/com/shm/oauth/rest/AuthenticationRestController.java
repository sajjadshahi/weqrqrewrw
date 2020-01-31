package com.shm.oauth.rest;

import com.shm.common.dto.auth.CheckAuthenticationDto;
import com.shm.common.dto.auth.ConfirmOtpDto;
import com.shm.common.dto.base.ResultDto;
import com.shm.common.dto.base.ResultErrorCode;
import com.shm.common.exception.InvalidPhoneNumberException;
import com.shm.common.util.CryptoUtils;
import com.shm.common.util.PhoneUtils;
import com.shm.oauth.exception.AuthenticationFailedException;
import com.shm.oauth.factory.authentication.AuthenticationFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping(value = "rest/oauth/auth")
public class AuthenticationRestController {
    private static DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationRestController.class);

    @Value("${application.encryption.key}")
    String encryptionKey;

    @Autowired
    AuthenticationFactory authenticationFactory;


    @RequestMapping(value = "/check", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultDto> check(@RequestBody CheckAuthenticationDto authDto) {
        try {
            String userName = authenticationFactory.getAuthenticationMethod(false).isAuthenticated(authDto.getAuthToken());
            return new ResponseEntity<>(new ResultDto(true, userName, null, ""), HttpStatus.OK);
        } catch (AuthenticationFailedException e) {
            return new ResponseEntity<>(new ResultDto(false, "", ResultErrorCode.FORBIDDEN, "Authentication Failed!"), HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResultDto(false, "", ResultErrorCode.INTERNAL_SERVER_ERROR, "Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/create-token", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<ResultDto> confirmOTP(@RequestBody String requestBody) {
        try {
            CryptoUtils cryptoUtils = new CryptoUtils(encryptionKey);

            ConfirmOtpDto confirmOtpDto = cryptoUtils.decrypt(requestBody, ConfirmOtpDto.class);
            confirmOtpDto.setMobile(PhoneUtils.makeStandardPhoneNumber(confirmOtpDto.getMobile()));
            Date requestDate = dateFormat.parse(confirmOtpDto.getDateTime());

            long currentTime = System.currentTimeMillis() - (1000 * 60 * 20);

            if (requestDate.getTime() < currentTime) {
                logger.info("request has been expired!");
                return new ResponseEntity<>(new ResultDto(false, "", ResultErrorCode.EXPIRED, "request has been expired!"), HttpStatus.BAD_REQUEST);
            } else {

                String userName = (confirmOtpDto.getUserName() == null || confirmOtpDto.getUserName().equals("")) ? confirmOtpDto.getMobile() : confirmOtpDto.getUserName();

                String token = authenticationFactory.getAuthenticationMethod(false).authenticate(userName);

                return new ResponseEntity<>(new ResultDto(true, token, null, ""), HttpStatus.OK);
            }
        } catch (InvalidPhoneNumberException e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(new ResultDto(false, "", ResultErrorCode.INVALID_PHONE, "invalid phone number"), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(new ResultDto(false, "", ResultErrorCode.INTERNAL_SERVER_ERROR, "Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
