package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.Employee;

public interface EmpRepo extends JpaRepository<Employee, Integer>{
	List<Employee> findByDepartment(String department);

}
