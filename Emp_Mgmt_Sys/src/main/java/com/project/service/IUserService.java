package com.project.service;

import java.util.List;

import com.project.model.User;

public interface IUserService {
	public List<User> getUserByDepartment(String department);

}
