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
	private static final int MIN_EMAIL_LENGTH = 5;
	private static final int MAX_EMAIL_LENGTH = 50;
	private static final int MIN_PASSWORD_LENGTH = 5;
	private static final int MAX_PASSWORD_LENGTH = 80;

	public boolean isLoginValid(UserLoginDto userLoginDto){
        if (userLoginDto != null) { 
        	String userLoginEmail = userLoginDto.getEmail();
        	String userLoginPassword = DigestUtils.md5Hex(userLoginDto.getPassword()).concat(getSalt());
        	
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
	
    public boolean registerValidation(UserRegistrationDto userForRegister) {

        User user = userService.getUserByEmail(userForRegister.getEmail());
        if (user == null) {
            String password = DigestUtils.md5Hex(userForRegister.getPassword()).concat(getSalt());

            user = new User(userForRegister.getEmail(),
                    userForRegister.getFirstName(),
                    userForRegister.getLastName(),
                    password);
            return userForRegister.getConfirmPassword().equals(userForRegister.getPassword())
                    && userService.create(user) != null;
        }

        return false;
    }

    private String getSalt() {
        Random random = new SecureRandom();
        StringBuilder salt = new StringBuilder();
        for (int i = 0; i < 8; i++)
            salt.append(random.nextInt(9));
        return salt.toString();
    }

}
