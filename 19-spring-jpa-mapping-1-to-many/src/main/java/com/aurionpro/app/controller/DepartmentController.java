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

import com.aurionpro.app.dto.DepartmentCreateRequest;
import com.aurionpro.app.dto.DepartmentResponse;
import com.aurionpro.app.dto.DepartmentUpdateRequest;
import com.aurionpro.app.service.DepartmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

	private final DepartmentService service;

	public DepartmentController(DepartmentService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<DepartmentResponse> create(@Valid @RequestBody DepartmentCreateRequest body) {
		DepartmentResponse created = service.create(body);
		return ResponseEntity.created(URI.create("/api/departments/" + created.getId())).body(created);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DepartmentResponse> get(@PathVariable Long id) {
		return ResponseEntity.ok(service.get(id));
	}

	@GetMapping
	public ResponseEntity<List<DepartmentResponse>> list() {
		return ResponseEntity.ok(service.list());
	}

	@PutMapping("/{id}")
	public ResponseEntity<DepartmentResponse> update(@PathVariable Long id,
			@Valid @RequestBody DepartmentUpdateRequest body) {
		return ResponseEntity.ok(service.update(id, body));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
