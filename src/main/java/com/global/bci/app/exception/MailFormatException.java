package com.global.bci.app.exception;

public class MailFormatException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public MailFormatException(String mensaje) {
		super(mensaje);
	}
}
