package com.softserve.mosquito.validation;

import com.softserve.mosquito.dtos.UserLoginDto;
import com.softserve.mosquito.dtos.UserRegistrationDto;
import com.softserve.mosquito.enitities.User;
import com.softserve.mosquito.services.UserService;

public class Validation {

    private UserService userService = new UserService();

    public boolean loginValidation(UserLoginDto loginDto) {

        if (loginDto.getUsername().length() < 10 && loginDto.getUsername().length() < 25)
            return true;


        return false;
    }

    public boolean registerValidation(UserRegistrationDto user) {
        return user.getConfirmPassword().equals(user.getPassword()) && userService.create(null) != null;
    }

}
