package com.aurionpro.controller;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.DAO.EmployeeDAOImplementation;
import com.aurionpro.entity.Employee;
import com.aurionpro.service.EmployeeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class EmployeeController {


	private EmployeeService service;
	
	@Autowired
	public EmployeeController(EmployeeService service) {
		this.service=service;
	}
	
	@GetMapping("/employees")
	public List<Employee>getEmployees(){
		return service.getAllEmployees();
	}
	
	@GetMapping("/employees/{id}")
	public Employee getEmployeeById(@PathVariable int id) {
		if(id<=0) {
			throw new RuntimeException("Invalid ID");
		}
		
		return service.getEmployeeById(id);
	}
	
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee employee) {
		employee.setId(0);
		return service.addEmployee(employee);
	}
	
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee employee) {
		
		return service.updateEmployee(employee);
	}
}
