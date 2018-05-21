package com.softserve.mosquito.dtos;

import java.util.Set;

import com.softserve.mosquito.enitities.Specialization;

public class UserUpdateDto extends UserRegistrationDto {
	private Set<Specialization> specializations;

	public UserUpdateDto() {
	}

	public UserUpdateDto(String email, String firstName, String lastName, String password, String confirmPassword, Set<Specialization> specializations) {
		super(email, firstName, lastName, password, confirmPassword);
		this.specializations = specializations;
	}

	public Set<Specialization> getSpecializations() {
		return specializations;
	}

	public void setSpecializations(Set<Specialization> specializations) {
		this.specializations = specializations;
	}	
	
}
