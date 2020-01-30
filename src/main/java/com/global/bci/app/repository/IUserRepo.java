package com.global.bci.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.global.bci.app.entity.User;

@Repository
public interface IUserRepo extends JpaRepository<User, Long>{

}
