package com.softserve.mosquito.validation;

import com.softserve.mosquito.dtos.UserLoginDto;
import com.softserve.mosquito.dtos.UserRegistrationDto;
import com.softserve.mosquito.enitities.User;
import com.softserve.mosquito.services.UserService;

public class UserValidation {
	private UserService userService = new UserService();

    public boolean isLoginValid(UserLoginDto userLoginDto){
        if (userLoginDto != null) { 
        	String userLoginEmail = userLoginDto.getEmail();
        	String userLoginPassword = userLoginDto.getPassword();
        	
        	if(userLoginEmail != null && userLoginEmail.length() > 10 && userLoginEmail.length() < 35 
        			&& userLoginPassword != null && userLoginPassword.length() > 5 && userLoginPassword.length() < 35) {
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
