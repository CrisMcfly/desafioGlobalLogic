package com.global.bci.app.exception;

public class ModelNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ModelNotFoundException(String mensaje) {
		super(mensaje);
	}

}
