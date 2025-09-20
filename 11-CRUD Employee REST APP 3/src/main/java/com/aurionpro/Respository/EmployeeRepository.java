package com.aurionpro.Respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yaml.snakeyaml.events.Event.ID;

import com.aurionpro.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
