package com.global.bci.app.exception;

public class PasswordFormatException extends RuntimeException{

private static final long serialVersionUID = 1L;
	
	public PasswordFormatException(String mensaje) {
		super(mensaje);
	}

}
