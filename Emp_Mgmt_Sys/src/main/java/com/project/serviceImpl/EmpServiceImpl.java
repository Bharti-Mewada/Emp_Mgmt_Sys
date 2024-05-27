package com.project.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Employee;
import com.project.repository.EmpRepo;
import com.project.service.IEmpService;

@Service
public class EmpServiceImpl implements IEmpService {
	@Autowired
	private EmpRepo empRepo;

	@Override
	public Employee addEmp(Employee emp) {

		Employee employee = empRepo.save(emp);
		return employee;

	}

	@Override
	public List<Employee> getAllEmp() {
		List<Employee> empList= empRepo.findAll();
		return empList;
	}

	@Override
	public String updateEmp(Employee emp) {
		Optional<Employee> ExistEmployee = empRepo.findById(emp.getId());
		if(ExistEmployee.isPresent()) {
		Employee updateEmployee=	ExistEmployee.get();
		updateEmployee.setName(emp.getName());
		updateEmployee.setAge(emp.getAge());
		updateEmployee.setDepartment(emp.getDepartment());
		updateEmployee.setSalary(emp.getSalary());
		updateEmployee.setState(emp.getState());
		empRepo.save(updateEmployee);
		return "Employee Updated Successfully";
		}
		else {
			return "Employee Not Found.";
		}
	}

	@Override
	public String removeEmp(Integer id) {
		empRepo.deleteById(id);
		return "Data Deleted Successfully.";
	}

	@Override
	public Optional<Employee> findEmpById(Integer id) {
		Optional<Employee> emp = empRepo.findById(id);
		if(emp.isPresent()) {
			return emp;
		}
		return null;
	}

	@Override
	public List<Employee> findEmpByDepartment(String departmeent) {
		return empRepo.findByDepartment(departmeent);
	}

}
