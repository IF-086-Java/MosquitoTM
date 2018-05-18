package com.softserve.mosquito.validation;

import com.softserve.mosquito.dtos.UserLoginDto;
import com.softserve.mosquito.dtos.UserRegistrationDto;
import com.softserve.mosquito.enitities.User;
import com.softserve.mosquito.services.UserService;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.SecureRandom;
import java.util.Random;

public class UserValidation {
    private UserService userService = new UserService();
    private String salt = "r4OSxKpY";

    public boolean isLoginValid(UserLoginDto userLoginDto) {
        if (userLoginDto != null) {
            String userLoginEmail = userLoginDto.getEmail();
            String userLoginPassword = userLoginDto.getPassword();

            if (userLoginEmail != null && userLoginEmail.length() > 10 && userLoginEmail.length() < 35
                    && userLoginPassword != null && userLoginPassword.length() > 5 && userLoginPassword.length() < 35) {
                User user = userService.getUserByEmail(userLoginDto.getEmail());

                if (user != null) {
                    String userEmail = user.getEmail();
                    String userPassword = user.getPassword();
                    if (userLoginEmail.equals(userEmail) && userLoginPassword.equals(userPassword)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean registerValidation(UserRegistrationDto userForRegister) {

        User user = userService.getUserByEmail(userForRegister.getEmail());
        if (user == null && userForRegister.getConfirmPassword().equals(userForRegister.getPassword())) {
            String password = DigestUtils.md5Hex(userForRegister.getPassword().concat(salt));

            user = new User(userForRegister.getEmail(),
                    userForRegister.getFirstName(),
                    userForRegister.getLastName(),
                    password);
            return userService.create(user) != null;
        }

        return false;
    }

}
