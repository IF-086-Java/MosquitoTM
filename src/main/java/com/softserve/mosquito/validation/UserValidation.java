package com.softserve.mosquito.validation;

import com.softserve.mosquito.dtos.UserLoginDto;
import com.softserve.mosquito.dtos.UserRegistrationDto;
import com.softserve.mosquito.enitities.User;
import com.softserve.mosquito.services.UserService;

public class UserValidation {
	private UserService userService;
	private static final int MIN_EMAIL_LENGTH = 6;
	private static final int MAX_EMAIL_LENGTH = 30;
	private static final int MIN_PASSWORD_LENGTH = 5;
	private static final int MAX_PASSWORD_LENGTH = 30;

    public UserValidation(UserService userService) {
		this.userService = userService;
	}

	public boolean isLoginValid(UserLoginDto userLoginDto){
        if (userLoginDto != null) { 
        	String userLoginEmail = userLoginDto.getEmail();
        	String userLoginPassword = userLoginDto.getPassword();
        	
        	if(userLoginEmail != null && userLoginEmail.length() >= MIN_EMAIL_LENGTH && userLoginEmail.length() <= MAX_EMAIL_LENGTH 
        			&& userLoginPassword != null && userLoginPassword.length() >= MIN_PASSWORD_LENGTH && userLoginPassword.length() <= MAX_PASSWORD_LENGTH) {
        		User user = userService.getUserByEmail(userLoginDto.getEmail());
        		        		
        		if(user != null) {
        			String userEmail = user.getEmail();
        			String userPassword = user.getPassword();
        			if(userLoginEmail.equals(userEmail) && userLoginPassword.equals(userPassword)) {
        				return true;
        			}
        		}
        	} 	
        }
        return false;
    }

    public boolean registerValidation(UserRegistrationDto registrationDto) {

        if (registrationDto.getEmail().matches(""))
            if (registrationDto.getConfirmPassword().equals(registrationDto.getPassword()))
                return true;

        return false;
    }

}
