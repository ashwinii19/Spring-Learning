package com.aurionpro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.dto.EmployeeRequestDTO;
import com.aurionpro.dto.EmployeeResponseDTO;
import com.aurionpro.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	private EmployeeService service;
	
	@Autowired
	public EmployeeController(EmployeeService service) {
		this.service=service;
	}
	
	@PostMapping("/employees")
	public ResponseEntity<EmployeeResponseDTO> createEmployee(@Validated @RequestBody EmployeeRequestDTO requestDTO) {
		return ResponseEntity.ok(service.createEmployee(requestDTO)); 
	}
	
	@GetMapping("/employees")
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
        return ResponseEntity.ok(service.getAllEmployees());
    }
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable Long id) {
		return ResponseEntity.ok(service.getEmployeeById(id));
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<EmployeeResponseDTO> updateEmployee(@Validated @PathVariable Long id,@RequestBody EmployeeRequestDTO requestDTO) {
		return ResponseEntity.ok(service.updateEmployee(id, requestDTO));
	}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
		service.deleteEmployee(id);
		return ResponseEntity.noContent().build();
	}
	
	

}
