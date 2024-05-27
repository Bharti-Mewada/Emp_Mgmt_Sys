package com.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	private String usernName;
	private String password;
	private String role;
	private String department;

	public User(Integer id, String usernName, String password, String role,String department ) {
		super();
		Id = id;
		this.usernName = usernName;
		this.password = password;
		this.role = role;
		this.department=department;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getUsernName() {
		return usernName;
	}
	public String getDepartment() {
		return department;
	}

	public void setUsernName(String usernName) {
		this.usernName = usernName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	public void setDepartment(String department) {
		this.department=department;
	}

}
