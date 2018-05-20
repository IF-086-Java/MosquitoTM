package com.softserve.mosquito.validation;

import org.apache.commons.codec.digest.DigestUtils;

import com.softserve.entities.User;
import com.softserve.mosquito.dtos.UserLoginDto;
import com.softserve.mosquito.dtos.UserRegistrationDto;
import com.softserve.mosquito.services.UserService;

public class UserValidation {

    private String salt = "r4OSxKpY";
    private UserService userService = new UserService();
    private static final int MIN_EMAIL_LENGTH = 5;
    private static final int MAX_EMAIL_LENGTH = 50;
    private static final int MIN_PASSWORD_LENGTH = 5;
    private static final int MAX_PASSWORD_LENGTH = 80;

    public boolean isLoginValid(UserLoginDto userLoginDto) {
        if (userLoginDto != null) {
            String userLoginEmail = userLoginDto.getEmail();
            String userLoginPassword = DigestUtils.md5Hex(userLoginDto.getPassword()).concat(salt);

            if (userLoginEmail != null && userLoginEmail.length() >= MIN_EMAIL_LENGTH && userLoginEmail.length() <= MAX_EMAIL_LENGTH
                    && userLoginPassword != null && userLoginPassword.length() >= MIN_PASSWORD_LENGTH && userLoginPassword.length() <= MAX_PASSWORD_LENGTH) {
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
