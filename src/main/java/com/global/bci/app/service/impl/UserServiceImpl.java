package com.global.bci.app.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.global.bci.app.auth.JwtTokenUtil;
import com.global.bci.app.entity.User;
import com.global.bci.app.repository.IUserRepo;
import com.global.bci.app.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private IUserRepo repo;
	
	
	@Override
	@Transactional()
	public User save(User obj) {
		obj.getPhones().forEach(p -> {
			p.setUser(obj);
		});
		repo.save(obj);
		Map<String, Object> claims = new HashMap<>();
		String token = jwtTokenUtil.doGenerateToken(claims, obj.getName());
		logger.info("Token de usuario creado con JWT: " + token);
		obj.setToken(token);	
		return repo.save(obj);
	}

	@Override
	@Transactional()
	public User update(User obj) {
		obj.getPhones().forEach(p -> {
			p.setUser(obj);
		});
		return repo.save(obj);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		return repo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public User findById(Long id) {
		Optional<User> op = repo.findById(id);
		return op.isPresent() ? op.get() : new User();
	}

	@Override
	@Transactional()
	public boolean delete(Long id) {
		repo.deleteById(id);
		return true;
	}

}
