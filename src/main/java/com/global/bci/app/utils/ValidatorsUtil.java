package com.global.bci.app.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ValidatorsUtil {

	private static Logger logger = LoggerFactory.getLogger(ValidatorsUtil.class);
	
	public static Boolean validateMail(String mail) {
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(mail);
		if (matcher.matches()) {
			logger.info("Formato email validado: " + matcher.matches());
			return true;	
		}else {
			logger.error("Formato de correo incorrecto");
			return false;
		}
	}
	
	public static Boolean validatePass(String pass) {
		String regex = "([A-Z]{1})([a-z]*)([0-9]{2})";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(pass);
		if (matcher.matches()) {
			logger.info("Formato de contraseña validado: " + matcher.matches());
			return true;	
		}else {
			logger.error("Formato de contraseña incorrecto");
			return false;
		}
	}
}
