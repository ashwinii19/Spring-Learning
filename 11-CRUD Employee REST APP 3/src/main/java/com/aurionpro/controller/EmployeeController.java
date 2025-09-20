package com.aurionpro.controller;

import java.util.List;
import java.util.Map;

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
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	
//	@PatchMapping("/employees/{id}")
//	public Employee updateEmployee(@PathVariable int id,@RequestBody Map<String,Object>patchPayLoad) {
//		Employee tempEmployee=service.getEmployeeById(id);
//		
//		if(tempEmployee==null) {
//			throw new RuntimeException("Employee Id not found-"+id);
//		}
//		
//		if(patchPayLoad.containsKey("id")) {
//			throw new RuntimeException("Employee id not allowed in request body-"+id);
//		}
//		
//		Employee patchedEmployee=apply(patchPayLoad,tempEmployee);
//		
//		Employee dbEmployee= service.addEmployee(patchedEmployee);
//		
//		return dbEmployee;
//	
//		
//	} 
//	
//	private Employee apply(Map<String, Object> patchPayLoad, Employee tempEmployee) {
//		
//		ObjectNode employeeNode=objectMapper.convertValue(tempEmployee, ObjectNode.class);//employee object to JSON object node
//		
//		ObjectNode patchNode=objectMapper.convertValue(patchPayLoad,ObjectNode.class);//patchpayload object to JSON object node
//		
//		employeeNode.setAll(patchNode);//merge patch updates into employee node
//		
//		return objectMapper.convertValue(employeeNode, Employee.class);//json object node back to Employee object
//	}
	
	
	@PatchMapping("/employees/{id}")
	public Employee partialUpdateEmployee(@PathVariable int id,@RequestBody Map<String,Object>updates) throws JsonMappingException  {
		
		return service.partialUpdateEmployee(id, updates);
	}
	
	@PutMapping("/employees/{id}")
	public Employee updateEmployee(@PathVariable int id,@RequestBody Employee employee) {
		
		return service.updateEmployee(id, employee);
	}

	@DeleteMapping("/employees/{id}")
	public void deleteEmployee(@PathVariable int id) {
		 service.deleteById(id);
	}
	
	 
	
	
	
}
