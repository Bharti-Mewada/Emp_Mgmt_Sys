package com.project.service;

import java.util.List;
import java.util.Optional;

import com.project.model.Employee;

public interface IEmpService  {
	public Employee addEmp(Employee emp);

	public List<Employee> getAllEmp();

	public String updateEmp(Employee emp);

	public String removeEmp(Integer id);

	public Optional<Employee> findEmpById(Integer id);

	List<Employee> findEmpByDepartment(String departmeent);
}
