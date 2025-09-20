package com.aurionpro.DAO;

import java.util.List;

import com.aurionpro.entity.Employee;



public interface EmployeeDAO {

	public List<Employee>getAllEmployees();
	
	public Employee getEmployeeById(int id);
	
	
	public Employee addEmployee(Employee employee);
			
		
	
}
