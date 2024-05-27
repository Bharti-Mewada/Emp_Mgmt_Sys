package com.project.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.model.User;
import com.project.repository.UserRepo;
import com.project.service.IUserService;

public class UserServiceImpl implements IUserService{
	@Autowired
	private UserRepo userRepo;

	@Override
	public List<User> getUserByDepartment(String department) {
//		List<User> user =userRepo.findByDe
		return null;
	}

}
