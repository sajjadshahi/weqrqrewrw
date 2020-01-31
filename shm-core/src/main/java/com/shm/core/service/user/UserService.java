package com.shm.core.service.user;

import com.shm.authutil.service.AuthenticationManager;
import com.shm.common.dto.base.ResultDto;
import com.shm.common.exception.InvalidPhoneNumberException;
import com.shm.common.exception.user.BadInfoException;
import com.shm.common.exception.user.UserNotFoundException;
import com.shm.core.domain.dto.user.LoginResultDto;
import com.shm.core.domain.dto.user.UserDto;
import com.shm.core.domain.entity.user.User;
import com.shm.core.domain.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;


    private UserDto getDto(User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getMobile(),
                user.getCreated(),
                user.getModified());
    }

    private User getByMobile(String mobile) throws UserNotFoundException {
        User user = userRepository.getByMobile(mobile);
        if (user == null)
            throw new UserNotFoundException();
        return user;
    }

    private User getByMobileAndPassword(String mobile, String password) throws UserNotFoundException {
        User user = userRepository.findByMobileAndPassword(mobile, password);
        if (user == null)
            throw new UserNotFoundException();
        return user;
    }
    public List<UserDto> getUsersList() {
        ArrayList<UserDto> dtos = new ArrayList<>();

        userRepository.findAll().forEach(user -> {
            UserDto dto = new UserDto();
            dto.setFirstName(user.getFirstName());
            dto.setLastName(user.getLastName());
            dto.setMobile(user.getMobile());
            dto.setId(user.getId());

            dtos.add(dto);
        });

        return dtos;
    }
    public ResultDto signup(String mobile, String password) throws BadInfoException {
        try {
            // Check if tel is null or empty
            if (mobile == null || mobile.isEmpty())
                throw new BadInfoException("Bad Tel no. or code.");


            User user = null;
            // Create new User
            user = new User();
            user.setMobile(mobile);
            user.setPassword(password);
            user.setCreated(new Date());
            user.setModified(new Date());

            // Save new User
            userRepository.save(user);

            return new ResultDto(true, "Signup Done");
        } catch (Exception e) {
            throw new BadInfoException("Bad Tel no. or code.");
        }
    }

    public LoginResultDto login(String mobile, String password) throws BadInfoException, UnAuthorizedAccessException, IncorrectCodeException, UserNotFoundException {
        try {
            if (mobile == null || password == null || mobile.isEmpty() || password.isEmpty())
                throw new BadInfoException("Bad Tel no. or code.");

            User user = getByMobileAndPassword(mobile, password);

            if (user == null)
                throw new UnAuthorizedAccessException("Unauthorized Access.");
            else { // User exists
                // Check if codes match
                String token = authenticationManager.createToken(mobile, password, user.getId() + "");

                return new LoginResultDto(true, "Token created.", token);
            }
        } catch (InvalidPhoneNumberException e) {
            throw new BadInfoException("Bad Tel no. or code.");
        } catch (WrongCodeException e) {
            throw new IncorrectCodeException("Incorrect Code.");
        }
    }

    public UserDto getInfo(String mobile) throws UserNotFoundException {
        User user = getByMobile(mobile);
        return getDto(user);
    }

    public UserDto updateInfo(User user, String firstName, String lastName, String email) {
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setModified(new Date());
        userRepository.save(user);
        return getDto(user);
    }

    public User getById(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id).orElse(null);
        if (user == null)
            throw new UserNotFoundException();
        return user;
    }


}
