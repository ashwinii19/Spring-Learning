package com.aurionpro.app.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "departments")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String name;

	// Inverse side
	@OneToMany(mappedBy = "department", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, // no REMOVE
			orphanRemoval = false)
	private List<Employee> employees = new ArrayList<>();

	public void addEmployee(Employee e) {
		employees.add(e);
		e.setDepartment(this);
	}

	public void removeEmployee(Employee e) {
		employees.remove(e);
		e.setDepartment(null);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Employee> getEmployees() {
		return employees;
	}
}
