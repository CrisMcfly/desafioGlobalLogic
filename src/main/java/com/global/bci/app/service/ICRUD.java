package com.global.bci.app.service;

import java.util.List;

public interface ICRUD <T>{

	T save(T obj);
	T update(T obj);
	List<T> findAll();
	T findById(Long id);
	boolean delete(Long id);
	
}
