package com.aurionpro.app.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.app.dto.EmployeeCreateRequest;
import com.aurionpro.app.dto.EmployeeResponse;
import com.aurionpro.app.dto.EmployeeUpdateRequest;
import com.aurionpro.app.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	private final EmployeeService service;

	public EmployeeController(EmployeeService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<EmployeeResponse> create(@Valid @RequestBody EmployeeCreateRequest body) {
		EmployeeResponse created = service.create(body);
		return ResponseEntity.created(URI.create("/api/employees/" + created.getId())).body(created);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeResponse> get(@PathVariable Long id) {
		return ResponseEntity.ok(service.get(id));
	}

	@GetMapping
	public ResponseEntity<List<EmployeeResponse>> list() {
		return ResponseEntity.ok(service.list());
	}

	@PutMapping("/{id}")
	public ResponseEntity<EmployeeResponse> update(@PathVariable Long id,
			@Valid @RequestBody EmployeeUpdateRequest body) {
		return ResponseEntity.ok(service.update(id, body));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	// Optional explicit move endpoint
	@PutMapping("/{id}/department/{deptId}")
	public ResponseEntity<EmployeeResponse> move(@PathVariable Long id, @PathVariable Long deptId) {
		return ResponseEntity.ok(service.moveToDepartment(id, deptId));
	}
}
