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

import com.aurionpro.app.dto.ProfileCreateRequest;
import com.aurionpro.app.dto.ProfileUpdateRequest;
import com.aurionpro.app.dto.StudentCreateRequest;
import com.aurionpro.app.dto.StudentResponse;
import com.aurionpro.app.dto.StudentUpdateRequest;
import com.aurionpro.app.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/students")
public class StudentController {

	private final StudentService service;

	public StudentController(StudentService service) {
		this.service = service;
	}

	// Create student
	@PostMapping
	public ResponseEntity<StudentResponse> create(@Valid @RequestBody StudentCreateRequest request) {
		StudentResponse created = service.create(request);
		return ResponseEntity.created(URI.create("/api/students/" + created.getId())).body(created);
	}

	@GetMapping("/{id}")
	public ResponseEntity<StudentResponse> get(@PathVariable Long id) {
		return ResponseEntity.ok(service.get(id));
	}

	@GetMapping
	public ResponseEntity<List<StudentResponse>> list() {
		return ResponseEntity.ok(service.list());
	}

	@PutMapping("/{id}")
	public ResponseEntity<StudentResponse> update(@PathVariable Long id,
			@Valid @RequestBody StudentUpdateRequest request) {
		return ResponseEntity.ok(service.update(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	// Attach profile later
	@PostMapping("/{id}/profile")
	public ResponseEntity<StudentResponse> addProfile(@PathVariable Long id, @Valid @RequestBody ProfileCreateRequest profile) {
		StudentResponse updated = service.addProfile(id, profile);
		return ResponseEntity.ok(updated);
	}

	// Update existing profile
	@PutMapping("/{id}/profile")
	public ResponseEntity<StudentResponse> updateProfile(@PathVariable Long id,
			@Valid @RequestBody ProfileUpdateRequest profile) {
		StudentResponse updated = service.updateProfile(id, profile);
		return ResponseEntity.ok(updated);
	}
}
