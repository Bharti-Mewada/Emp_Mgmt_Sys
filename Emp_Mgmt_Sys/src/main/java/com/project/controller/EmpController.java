package com.project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Employee;
import com.project.service.IEmpService;

@RestController
@RequestMapping("/emp")
public class EmpController {

	@Autowired
	private IEmpService empService;

	@GetMapping("/home")
	public String home() {
		return "Welcome to the Employee Management System.";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/addEmp")
	public ResponseEntity<Employee> addEmp(@RequestBody Employee emp) {
		return new ResponseEntity<Employee>(empService.addEmp(emp), HttpStatus.CREATED);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/removeEmp")
	public ResponseEntity<String> removeEmp(@RequestParam Integer id) {
		return new ResponseEntity<String>(empService.removeEmp(id), HttpStatus.ACCEPTED);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/findEmpById")
	public ResponseEntity<Optional<Employee>> findEmpById(@RequestParam Integer id) {

		return new ResponseEntity<Optional<Employee>>(empService.findEmpById(id), HttpStatus.ACCEPTED);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/findEmpByDepartment")
	public ResponseEntity<List<Employee>> findEmpByDepartment(@RequestParam String department) {

		return new ResponseEntity<List<Employee>>(empService.findEmpByDepartment(department), HttpStatus.ACCEPTED);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getAllEmp")
	public ResponseEntity<List<Employee>> getAllEmp() {
		return new ResponseEntity<List<Employee>>(empService.getAllEmp(), HttpStatus.ACCEPTED);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/updateEmp")
	public ResponseEntity<String> updateEmp(@RequestBody Employee employee) {

		return new ResponseEntity<String>(empService.updateEmp(employee), HttpStatus.ACCEPTED);
	}

}
