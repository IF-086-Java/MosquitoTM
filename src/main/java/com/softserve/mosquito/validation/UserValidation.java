package com.softserve.mosquito.validation;

import com.softserve.mosquito.dtos.UserLoginDto;
import com.softserve.mosquito.dtos.UserRegistrationDto;
import com.softserve.mosquito.enitities.User;
import com.softserve.mosquito.services.UserService;
import org.apache.commons.codec.digest.DigestUtils;

public class UserValidation {
	private final static String EMAIL_REGEX = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,}$)";
	private final static int MIN_LENGTH_FIRST_NAME = 2;
	private final static int MIN_LENGTH_LAST_NAME = 2;
	private final static int MIN_LENGTH_PASSWORD = 7;
    
	private String salt = "r4OSxKpY";
    private UserService userService = new UserService();

    public boolean isValidCredentials(UserLoginDto userLoginDto) {
        if (isLoginDataValid(userLoginDto)) {
            String encryptedLoginPassword = DigestUtils.md5Hex(userLoginDto.getPassword().concat(salt));
            User user = userService.getUserByEmail(userLoginDto.getEmail());

            if (user != null && encryptedLoginPassword.equals(user.getPassword())) {
            	return true;
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
        return user.getEmail().matches(EMAIL_REGEX)
                && user.getFirstName().length() >= MIN_LENGTH_FIRST_NAME
                && user.getLastName().length() >= MIN_LENGTH_LAST_NAME
                && user.getPassword().length() >= MIN_LENGTH_PASSWORD
                && user.getConfirmPassword().length() >= MIN_LENGTH_PASSWORD
                && user.getConfirmPassword().equals(user.getPassword());

    }
    
    private boolean isLoginDataValid(UserLoginDto userLoginDto) {
    	return userLoginDto != null
    			&& userLoginDto.getEmail() != null
    			&& userLoginDto.getPassword() != null
    			&& userLoginDto.getEmail().matches(EMAIL_REGEX)
    			&& userLoginDto.getPassword().length() >= MIN_LENGTH_PASSWORD;
    }
}