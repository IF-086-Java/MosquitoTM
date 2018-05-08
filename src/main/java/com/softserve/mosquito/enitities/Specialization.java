package com.softserve.mosquito.enitities;

public enum Specialization {
	DEVELOPER(1L), ARCHITECT(2L), PROJECT_MANAGER(3L), TEST(4L);
	
	private Long specializationNumber;
	
	Specialization(Long specializationNumber){
		this.specializationNumber = specializationNumber;
	}
	
	public Long getSpecializationNumber() {
		return this.specializationNumber;
	}
}
