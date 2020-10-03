package com.global.bci.app.exception;

public class UserNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public UserNotFoundException(String mensaje) {
		super(mensaje);
	}

}
