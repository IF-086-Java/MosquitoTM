package com.softserve.mosquito.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
* DTO for user validation
 */

public class UserLoginDto {
	@NotNull
    @Pattern(regexp = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,}$)",
            message = "user.email.pattern")
	private String email;
	
	@NotNull
    @Size(min = 8, message = "user.password.pattern")
	private String password;
		
	public UserLoginDto() {
	}

	public UserLoginDto(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	

}
