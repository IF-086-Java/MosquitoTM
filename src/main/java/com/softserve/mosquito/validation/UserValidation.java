package com.softserve.mosquito.validation;

import com.softserve.mosquito.dtos.UserLoginDto;
import com.softserve.mosquito.dtos.UserRegistrationDto;
import com.softserve.mosquito.enitities.User;
import com.softserve.mosquito.services.UserService;
import org.apache.commons.codec.digest.DigestUtils;

public class UserValidation {

    private String salt = "r4OSxKpY";
    private UserService userService = new UserService();

    public boolean isValidCredentials(UserLoginDto userLoginDto) {
        if (userLoginDto != null && userLoginDto.getEmail() != null && userLoginDto.getPassword() != null) {
            String encryptedLoginPassword = DigestUtils.md5Hex(userLoginDto.getPassword().concat(salt));
            User user = userService.getUserByEmail(userLoginDto.getEmail());

            if (user != null) {
                if (encryptedLoginPassword.equals(user.getPassword())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean registerValidation(UserRegistrationDto userForRegister) {

        User user = userService.getUserByEmail(userForRegister.getEmail());

        if (user == null && isUserDataValid(userForRegister)) {
            String password = DigestUtils.md5Hex(userForRegister.getPassword().concat(salt));
            System.out.println(password);
            user = new User(userForRegister.getEmail(),
                    userForRegister.getFirstName(),
                    userForRegister.getLastName(),
                    password);
            return userService.create(user) != null;
        }

        return false;
    }

    private boolean isUserDataValid(UserRegistrationDto user) {
        return user.getEmail().matches("^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,}$)")
                && user.getFirstName().length() > 3
                && user.getLastName().length() > 3
                && user.getPassword().length() > 8
                && user.getConfirmPassword().length() > 8
                && user.getConfirmPassword().equals(user.getPassword());

    }
}