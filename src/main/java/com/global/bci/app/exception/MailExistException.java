package com.global.bci.app.exception;

public class MailExistException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public MailExistException(String mensaje) {
		super(mensaje);
	}
}
