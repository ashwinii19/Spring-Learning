package com.aurionpro.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.aurionpro.Respository.EmployeeRepository;
import com.aurionpro.entity.Employee;
import com.aurionpro.exception.EmployeeNotFoundException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
	private  ObjectMapper objectMapper;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository,ObjectMapper objectMapper) {
		this.employeeRepository = employeeRepository;
		this.objectMapper=objectMapper;
	}

	@Override
	public List<Employee> getEmployees() {

		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(int id) {
//
//		Employee emp = employeeRepository.findById(id);
//		if (emp == null) {
//			throw new EmployeeNotFoundException("Employee with ID " + id + " not found");
//		}
//		return emp;
//		
//		Optional<Employee> emp= employeeRepository.findById(id);
//		
//		return emp.orElse(null);
		
		 return employeeRepository.findById(id)
	                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id " + id));
		
	}

	@Override
	public Employee addEmployee(Employee employee) {

		return employeeRepository.save(employee);
	}

	@Override
	public Employee updateEmployee(int id, Employee employee) {

		Employee existing = getEmployeeById(id);
		if (employee.getFirstName() != null && !employee.getFirstName().isBlank()) {
			existing.setFirstName(employee.getFirstName());
		}

		if (employee.getLastName() != null && !employee.getLastName().isBlank()) {
			existing.setLastName(employee.getLastName());
		}

		if (employee.getEmail() != null && !employee.getEmail().isBlank()) {
			existing.setEmail(employee.getEmail());
		}

		employeeRepository.save(existing);
		return existing;

	}

	@Override
	public void deleteById(int id) {
		
		employeeRepository.deleteById(id);
	}

	@Override
	public Employee partialUpdateEmployee(int id, Map<String, Object> updates) throws JsonMappingException {
		Employee existing=getEmployeeById(id);
		
		if(existing==null) {
			throw new RuntimeException("Employee Id not found-"+id);
		}
		
		if(updates.containsKey("id")) {
			throw new RuntimeException("Employee id not allowed in request body-"+id);
		}
		
		Employee patched=objectMapper.updateValue(existing, updates);
		
		return employeeRepository.save(patched);
		
		
		
	}

}
