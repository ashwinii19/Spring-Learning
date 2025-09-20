package com.aurionpro.service;

import java.util.List;

import com.aurionpro.entity.Employee;

public interface EmployeeService {

	public List<Employee>getEmployees();
	
	public Employee getEmployeeById(int id);
	
	public Employee addEmployee(Employee employee);
	
	public Employee updateEmployee(int id,Employee employee);
	
	public void deleteById(int id);
}
