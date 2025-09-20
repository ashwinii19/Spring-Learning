package com.aurionpro.app.service;

import org.springframework.data.domain.Page;

import com.aurionpro.app.dto.CourseCreateRequest;
import com.aurionpro.app.dto.CourseResponse;
import com.aurionpro.app.dto.CourseUpdateRequest;

public interface CourseService {
	CourseResponse create(CourseCreateRequest req);

	CourseResponse get(Long id);

	Page<CourseResponse> list(int page, int size, String sortBy, String direction);

	CourseResponse update(Long id, CourseUpdateRequest req);
	
	
}
