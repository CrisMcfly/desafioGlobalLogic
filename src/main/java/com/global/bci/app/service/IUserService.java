package com.global.bci.app.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.global.bci.app.entity.User;

public interface IUserService {

	public ResponseEntity<User> save(User obj);
	public ResponseEntity<List<User>> findAll();
}
