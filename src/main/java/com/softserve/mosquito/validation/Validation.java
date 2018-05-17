package com.softserve.mosquito.validation;

import com.softserve.mosquito.dtos.UserRegistrationDto;
import com.softserve.mosquito.services.UserService;

public class Validation {

    private UserService userService = new UserService();


    public boolean registerValidation(UserRegistrationDto user) {
        return user.getConfirmPassword().equals(user.getPassword()) && userService.create(null) != null;
    }

}
