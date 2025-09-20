package com.aurionpro.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import com.aurionpro.entity.Employee;

import jakarta.persistence.EntityManager;

@Repository
public class EmployeeDAOImplementation implements EmployeeDAO {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	public EmployeeDAOImplementation(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Employee> getAllEmployees() {

		return entityManager.createQuery("from Employee", Employee.class).getResultList();
	}

	@Override
	public Employee getEmployeeById(int id) {
		
		
		return entityManager.find(Employee.class, id);
	}

	@Override
	public Employee addEmployee(Employee employee) {
		return entityManager.merge(employee);
		
	}

}
