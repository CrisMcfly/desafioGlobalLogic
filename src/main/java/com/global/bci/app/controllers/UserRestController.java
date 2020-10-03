package com.global.bci.app.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.bci.app.entity.User;
import com.global.bci.app.service.IUserService;


@RestController
@RequestMapping(value = {"/api/users"})
public class UserRestController {

	private Logger logger = LoggerFactory.getLogger(UserRestController.class);
	
	@Autowired
	private IUserService userService;

	@PostMapping()
	public ResponseEntity<User> create(@Valid @RequestBody User user){
		return userService.save(user);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<User>> findAll() {
		return userService.findAll();
	}
	
}