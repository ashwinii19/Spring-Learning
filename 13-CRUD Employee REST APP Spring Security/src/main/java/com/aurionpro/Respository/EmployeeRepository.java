package com.aurionpro.Respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.yaml.snakeyaml.events.Event.ID;

import com.aurionpro.entity.Employee;

@RepositoryRestResource(path="employees")
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
