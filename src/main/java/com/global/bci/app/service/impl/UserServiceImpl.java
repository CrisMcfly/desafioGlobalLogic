package com.global.bci.app.service.impl;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.global.bci.app.auth.JwtTokenUtil;
import com.global.bci.app.entity.User;
import com.global.bci.app.exception.MailExistException;
import com.global.bci.app.exception.MailFormatException;
import com.global.bci.app.exception.PasswordFormatException;
import com.global.bci.app.exception.UserNotFoundException;
import com.global.bci.app.repository.IUserRepo;
import com.global.bci.app.service.IUserService;
import com.global.bci.app.utils.ValidatorsUtil;

@Service
public class UserServiceImpl implements IUserService{

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private IUserRepo repo;
	
	@Override
	@Transactional()
	public ResponseEntity<User> save(User user) {
		User newUser = null;
		ResponseEntity<User> response = null;
		try {
			user.getPhones().forEach(p -> {
				p.setUser(user);
			});
			if (ValidatorsUtil.validateMail(user.getEmail())) {
				if (ValidatorsUtil.validatePass(user.getPassword())) {
					user.setCreateAt(Date.from(Instant.now()));
					user.setLastLoginAt(Date.from(Instant.now()));
					user.setIsActive(true);
					newUser = repo.save(user);
					Map<String, Object> claims = new HashMap<>();
					String token = jwtTokenUtil.doGenerateToken(claims, newUser.getName());
					logger.info("Token de usuario creado con JWT");
					newUser.setToken(token);	
					newUser = repo.save(newUser);
					logger.info("Usuario creado correctamente");
					response = new ResponseEntity<User>(newUser, HttpStatus.CREATED);
				}else {
					throw new PasswordFormatException("El formato de la Password es incorrecto");
				}
			}else {
				throw new MailFormatException("El formato del correo ingresado es incorrecto");
			}
		} catch (DataIntegrityViolationException e2) {
			logger.error("El Correo ingresado ya se encuentra registrado en la base de datos" + e2.getMessage());
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			throw new MailExistException("El correo ingresado ya existe en la base de datos");
		}
		catch (DataAccessException e) {
			logger.error("Ha Ocurrido un error en el metodo [save]" + e.getMessage());
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<List<User>> findAll() {
		List<User> lstResponse = null;
		ResponseEntity<List<User>> response = null;
		try {
			lstResponse = repo.findAll();
			if (lstResponse.isEmpty()) {
				logger.error("No se encuentran registros en la base de datos");
				throw new UserNotFoundException("No se encuentran registros en la base de datos");
			}else {
				response = new ResponseEntity<List<User>>(lstResponse, HttpStatus.OK);
			} 
		} catch (DataAccessException e) {
			logger.error("Ha Ocurrido un error en el metodo [findAll]" + e.getMessage());
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

}
