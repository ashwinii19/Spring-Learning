package com.aurionpro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.DAO.EmployeeDAOImplementation;
import com.aurionpro.entity.Employee;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	
private EmployeeDAOImplementation employeeDAO;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeDAOImplementation employeeDAO) {
		this.employeeDAO=employeeDAO;
	}
	

	@Override
	public List<Employee> getAllEmployees() {
		
		return employeeDAO.getAllEmployees();
	}

	@Override
	public Employee getEmployeeById(int id) {
		
		return employeeDAO.getEmployeeById(id);
	}


	@Override
	@Transactional
	public Employee addEmployee(Employee employee) {
		
		return employeeDAO.addEmployee(employee);
	}


	@Override
	@Transactional
	public Employee updateEmployee(Employee employee) {
		
		return employeeDAO.addEmployee(employee);
	}

	
}
