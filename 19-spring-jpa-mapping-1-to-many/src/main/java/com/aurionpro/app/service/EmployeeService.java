package com.aurionpro.app.service;

import java.util.List;

import com.aurionpro.app.dto.EmployeeCreateRequest;
import com.aurionpro.app.dto.EmployeeResponse;
import com.aurionpro.app.dto.EmployeeUpdateRequest;

public interface EmployeeService {
	EmployeeResponse create(EmployeeCreateRequest request);

	EmployeeResponse get(Long id);

	List<EmployeeResponse> list();

	EmployeeResponse update(Long id, EmployeeUpdateRequest request);

	void delete(Long id);

	EmployeeResponse moveToDepartment(Long empId, Long newDeptId);
}
