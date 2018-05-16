package com.softserve.mosquito.validation;

import com.softserve.mosquito.dtos.UserLoginDto;
import com.softserve.mosquito.dtos.UserRegistrationDto;

public class Validation {

    public boolean loginValidation(UserLoginDto loginDto){

        if (loginDto.getUsername().length() < 10&& loginDto.getUsername().length() < 25)
            return true;


        return false;
    }

    public boolean registerValidation(UserRegistrationDto registrationDto) {

        if (registrationDto.getEmail().matches(""))
            if (registrationDto.getConfirmPassword().equals(registrationDto.getPassword()))
                return true;

        return false;
    }

}
