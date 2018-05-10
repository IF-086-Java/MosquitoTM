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
	@Override
	public String toString() {
		String enumTitle = this.name();
		if(enumTitle.length() <= 1) {
			return enumTitle;
		}
		return enumTitle.charAt(0) + enumTitle.substring(1).replaceAll("_", " ").toLowerCase();
	}
}
