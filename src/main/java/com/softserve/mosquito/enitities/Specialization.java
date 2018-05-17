package com.softserve.mosquito.enitities;

import java.util.Objects;

public final class Specialization {
	private  Byte specializationId;
	private  String title;

	public Specialization() {
	}

	public Specialization(Byte specializationId) {
		this.specializationId = specializationId;
	}

	public Specialization(String title) {
		this.title = title;
	}

	public Specialization(Byte specializationId, String title) {
		this.specializationId = specializationId;
		this.title = title;
	}

	public Byte getSpecializationId() {
		return specializationId;
	}

	public void setSpecializationId(Byte specializationId) {
		this.specializationId = specializationId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Specialization that = (Specialization) o;
		return Objects.equals(specializationId, that.specializationId) &&
				Objects.equals(title, that.title);
	}

	@Override
	public int hashCode() {

		return Objects.hash(specializationId, title);
	}

	@Override
	public String toString() {
		return "Specialization{" +
				"specializationId=" + specializationId +
				", title='" + title + '\'' +
				'}';
	}
}
