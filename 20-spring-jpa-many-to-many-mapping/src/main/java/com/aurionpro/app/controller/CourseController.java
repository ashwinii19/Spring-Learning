package com.aurionpro.app.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.app.dto.CourseCreateRequest;
import com.aurionpro.app.dto.CourseResponse;
import com.aurionpro.app.dto.CourseUpdateRequest;
import com.aurionpro.app.service.CourseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {
	private final CourseService courseService;

	@PostMapping
	public ResponseEntity<CourseResponse> create(@Valid @RequestBody CourseCreateRequest req) {
		return ResponseEntity.ok(courseService.create(req));
	}

	@GetMapping("/{id}")
	public ResponseEntity<CourseResponse> get(@PathVariable Long id) {
		return ResponseEntity.ok(courseService.get(id));
	}

	@GetMapping
	public ResponseEntity<Page<CourseResponse>> list(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "title") String sortBy,
			@RequestParam(defaultValue = "asc") String direction) {
		return ResponseEntity.ok(courseService.list(page, size, sortBy, direction));
	}

	@PutMapping("/{id}")
	public ResponseEntity<CourseResponse> update(@PathVariable Long id, @RequestBody CourseUpdateRequest req) {
		return ResponseEntity.ok(courseService.update(id, req));
	}
}
