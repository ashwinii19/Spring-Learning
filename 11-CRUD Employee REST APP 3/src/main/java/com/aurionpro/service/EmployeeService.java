package com.aurionpro.service;

import java.util.List;
import java.util.Map;

import com.aurionpro.entity.Employee;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface EmployeeService {

	public List<Employee>getEmployees();
	
	public Employee getEmployeeById(int id);
	
	public Employee addEmployee(Employee employee);
	
	public Employee updateEmployee(int id,Employee employee);
	
	public Employee partialUpdateEmployee(int id,Map<String,Object> updates) throws JsonMappingException;
	
	public void deleteById(int id);
}
