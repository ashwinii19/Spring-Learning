package com.aurionpro.app.service;

import java.util.List;

import com.aurionpro.app.dto.DepartmentCreateRequest;
import com.aurionpro.app.dto.DepartmentResponse;
import com.aurionpro.app.dto.DepartmentUpdateRequest;

public interface DepartmentService {
	DepartmentResponse create(DepartmentCreateRequest request);

	DepartmentResponse get(Long id);

	List<DepartmentResponse> list();

	DepartmentResponse update(Long id, DepartmentUpdateRequest request);

	void delete(Long id);
}
