package com.shm.authutil.service;

import com.shm.authutil.Constants;
import com.shm.common.dto.auth.CheckAuthenticationDto;
import com.shm.common.dto.auth.ConfirmOtpDto;
import com.shm.common.dto.base.ResultDto;
import com.shm.common.util.CryptoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class SHMAuthenticationService {
    private static Logger logger = LoggerFactory.getLogger(SHMAuthenticationService.class);

    private static DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    public static ResultDto check(String authToken) {
        CheckAuthenticationDto request = new CheckAuthenticationDto();
        request.setTimestamp(System.currentTimeMillis());
        request.setDateTime(dateFormat.format(new Date()));
        request.setRequestId(UUID.randomUUID().toString());
        request.setAuthToken(authToken);

        try {
//            CryptoUtils cryptoUtils = new CryptoUtils(Constants.ENCRYPTION_KEY);
//
//            String encrypted = cryptoUtils.encrypt(request);

            RestTemplate restTemplate = new RestTemplate();
            String url = Constants.SHM_API_CHECK_URL;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<CheckAuthenticationDto> httpRequest = new HttpEntity<>(request, headers);

            ResponseEntity<ResultDto> responseEntity = restTemplate.postForEntity(url, httpRequest, ResultDto.class);

            return responseEntity.getBody();
        } catch (HttpServerErrorException e) {
            logger.error(e.getResponseBodyAsString());
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);

            return null;
        }

    }

    public static ResultDto createToken(String mobile, String password, String userName) {
        ConfirmOtpDto request = new ConfirmOtpDto();
        request.setTimestamp(System.currentTimeMillis());
        request.setDateTime(dateFormat.format(new Date()));
        request.setRequestId(UUID.randomUUID().toString());
        request.setMobile(mobile);
        request.setUserName(userName);
        request.setPassword(password);

        try {
            CryptoUtils cryptoUtils = new CryptoUtils(Constants.ENCRYPTION_KEY);

            String encrypted = cryptoUtils.encrypt(request);
//            Response<ResultDto> response = chimidoniAuthenticationAPI.confirmOtp(encrypted).execute();

            RestTemplate restTemplate = new RestTemplate();
            String url = Constants.SHM_API_CREATE_TOKEN_URL;
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> httpRequest = new HttpEntity<>(encrypted, headers);

            ResponseEntity<ResultDto> responseEntity = restTemplate.postForEntity(url, httpRequest, ResultDto.class);


            return responseEntity.getBody();
        } catch (HttpServerErrorException e) {
            logger.error(e.getResponseBodyAsString());
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }

    }

}
