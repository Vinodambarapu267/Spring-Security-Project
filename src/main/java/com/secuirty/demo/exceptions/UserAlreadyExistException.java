package com.secuirty.demo.exceptions;

public class UserAlreadyExistException extends RuntimeException{

	public UserAlreadyExistException(String message) {
		super(message);
	}

}
