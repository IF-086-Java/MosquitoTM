package com.softserve.mosquito.exceptions;

public class EntityNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 2816861894981365L;

	public EntityNotFoundException(String message) {
		super(message);
	}
}