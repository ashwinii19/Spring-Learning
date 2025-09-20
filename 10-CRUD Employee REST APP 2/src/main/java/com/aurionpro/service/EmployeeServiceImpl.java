package com.aurionpro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aurionpro.dao.EmployeeDAO;
import com.aurionpro.entity.Employee;
import com.aurionpro.exception.EmployeeNotFoundException;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDAO dao;

	public EmployeeServiceImpl(EmployeeDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<Employee> getEmployees() {

		return dao.findAll();
	}

	@Override
	public Employee getEmployeeById(int id) {

		Employee emp = dao.findById(id);
		if (emp == null) {
			throw new EmployeeNotFoundException("Employee with ID " + id + " not found");
		}
		return emp;
	}

	@Override
	public Employee addEmployee(Employee employee) {

		return dao.save(employee);
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

		dao.save(existing);
		return existing;

	}

	@Override
	public void deleteById(int id) {

		dao.deleteById(id);
	}

}
