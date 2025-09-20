package com.aurionpro.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aurionpro.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{
	
	private EntityManager entityManager;
	
	public EmployeeDAOImpl(EntityManager entityManager) {
		this.entityManager=entityManager;
	}

	@Override
	public List<Employee> findAll() {
		TypedQuery<Employee>query=entityManager.createQuery("from Employee",Employee.class);
		List<Employee>list=query.getResultList();
		
		return list;
	}

	@Override
	public Employee findById(int id) {
		
		return entityManager.find(Employee.class,id);
	}

	@Override
	public Employee save(Employee employee) {
		
		return entityManager.merge(employee);
	}

	@Override
	public void deleteById(int id) {
		Employee employee= entityManager.find(Employee.class,id);
		
		if(employee!=null) {
			entityManager.remove(employee);
		}
		
	}

}
