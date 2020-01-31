package com.shm.dashboard.rest;

import com.shm.authutil.exception.AuthenticationFailedException;
import com.shm.authutil.exception.TokenNotFoundException;
import com.shm.authutil.service.AuthenticationManager;
import com.shm.common.exception.user.UserNotFoundException;
import com.shm.core.domain.entity.user.User;
import com.shm.core.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ControllerAdvice
public class InitController {
    private Logger logger = LoggerFactory.getLogger(InitController.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @ModelAttribute("userLoggedIn")
    public User getLoginInformation(Model model,
                                    HttpSession httpSession,
                                    HttpServletRequest request) throws AuthenticationFailedException, TokenNotFoundException {
        if (request.getRequestURL().toString().contains("/error") ||
                request.getRequestURL().toString().contains("/login") ||
                request.getRequestURL().toString().contains("/signup"))
            return null;
        String token = request.getHeader("x-access-token");
//        System.out.println("\n\n\n\nTOKEN" + token);
        if (token == null)
            throw new TokenNotFoundException("No token provided.");
        String id = authenticationManager.isAuthenticated(token);

        User user = null;

        try {
            if (id != null) {
                user = userService.getById(Long.parseLong(id));
            }
        } catch (NumberFormatException | UserNotFoundException e) {
            logger.error(e.getMessage(), e);

            throw new AuthenticationFailedException();
        }

        return user;
    }
}
