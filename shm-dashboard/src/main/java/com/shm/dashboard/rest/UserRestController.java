package com.shm.dashboard.rest;

import com.shm.common.dto.base.ResponseDto;
import com.shm.common.dto.base.ResultDto;
import com.shm.common.exception.user.BadInfoException;
import com.shm.common.exception.user.UserNotFoundException;
import com.shm.core.domain.dto.user.LogDto;
import com.shm.core.domain.dto.user.LoginResultDto;
import com.shm.core.domain.dto.user.NotificationDto;
import com.shm.core.domain.dto.user.UserDto;
import com.shm.core.domain.entity.user.User;
import com.shm.core.service.LogService;
import com.shm.core.service.NotificationService;
import com.shm.core.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/user")
@CrossOrigin
public class UserRestController {

    @Autowired
    UserService userService;

    @Autowired
    NotificationService notificationService;
    @Autowired
    LogService logService;

    @RequestMapping("signup")
    public ResultDto signup(@RequestParam("mobile") String mobile, @RequestParam("password") String password) {
        try {
            return userService.signup(mobile, password);
        } catch (BadInfoException e) {
            e.printStackTrace();
            return new ResultDto(false, "bad info.");
        }
    }

    @RequestMapping("login")
    public LoginResultDto login(@RequestParam("mobile") String mobile, @RequestParam("password") String password) {
        try {
            return userService.login(mobile, password);
        } catch (BadInfoException e) {
            e.printStackTrace();
            return new LoginResultDto(false, "bad info.", "");
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return new LoginResultDto(false, "user not found.", "");
        }
    }

    @RequestMapping("user-info")
    public ResponseDto<UserDto> userInfo(@ModelAttribute("userLoggedIn") User userLoggedIn) {
        try {
            return new ResponseDto<>(userService.getInfo(userLoggedIn.getMobile()));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return new ResponseDto<>(false, "user not found.");
        }
    }

    @RequestMapping("users")
    public ResponseDto<List<UserDto>> usersList() {
        try {
            return new ResponseDto<>(userService.getUsersList());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDto<>(false, "Unknown Error");
        }
    }

    @RequestMapping("notifications")
    public ResponseDto<List<NotificationDto>> notificationsList() {
        try {
            return new ResponseDto<>(notificationService.getNotificationsList());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDto<>(false, "Unknown Error");
        }
    }


    @RequestMapping("notification/perform")
    public ResponseDto<String> performNotification(@RequestParam("id") Long notificationId) {
        try {
            return new ResponseDto<>(notificationService.performNotification(notificationId));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDto<>(false, "Unknown Error");
        }
    }
    @RequestMapping("notification/cancel")
    public ResponseDto<String> cancelNotification(@RequestParam("id") Long notificationId) {
        try {
            return new ResponseDto<>(notificationService.cancelNotification(notificationId));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDto<>(false, "Unknown Error");
        }
    }

    @RequestMapping("notification/create")
    public ResponseDto<String> createNotification(@RequestParam("title") String title,
                                                  @RequestParam("description") String description,
                                                  @RequestParam("priority") int priority,
                                                  @RequestParam("severity") int severity,
                                                  @RequestParam("medium") int media

                                                  ) {
        try {
            return new ResponseDto<>(notificationService.create(title, description, priority, severity, media));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDto<>(false, "Unknown Error");
        }
    }
    @RequestMapping("logs")
    public ResponseDto<List<LogDto>> logsList() {
        try {
            return new ResponseDto<>(logService.getLogsList());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDto<>(false, "Unknown Error");
        }
    }
}
