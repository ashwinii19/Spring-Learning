package com.aurionpro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.entity.Employee;
import com.aurionpro.service.EmployeeService;

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
		return service.getEmployees();
	}
	
	@GetMapping("/employees/{id}")
	public Employee getEmployeeById(@PathVariable int id) {
		return service.getEmployeeById(id);
	}
	
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee employee) {
		employee.setId(0);
		return service.addEmployee(employee);
	}
	
	
	@PatchMapping("/employees/{id}")
	public Employee updateEmployee(@PathVariable int id,@RequestBody Employee employee) {
		return service.updateEmployee(id, employee);
	}
	
	@DeleteMapping("/employees/{id}")
	public void deleteEmployee(@PathVariable int id) {
		 service.deleteById(id);
	}
	
	
	
	
	
}
