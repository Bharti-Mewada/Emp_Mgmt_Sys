package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.User;


public interface UserRepo extends JpaRepository<User, Integer> {
	String  findByUsernName(String usernName);
	
	

}
