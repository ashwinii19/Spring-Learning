package com.aurionpro.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aurionpro.app.dto.DepartmentCreateRequest;
import com.aurionpro.app.dto.DepartmentResponse;
import com.aurionpro.app.dto.DepartmentUpdateRequest;
import com.aurionpro.app.entity.Department;
import com.aurionpro.app.exception.NotFoundException;
import com.aurionpro.app.mapper.DepartmentMapper;
import com.aurionpro.app.repository.DepartmentRepository;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

	private final DepartmentRepository departments;

	public DepartmentServiceImpl(DepartmentRepository departments) {
		this.departments = departments;
	}

	@Override
	public DepartmentResponse create(DepartmentCreateRequest request) {
		Department saved = departments.save(DepartmentMapper.toNewEntity(request));
		return DepartmentMapper.toResponse(saved);
	}

	@Override
	@Transactional(readOnly = true)
	public DepartmentResponse get(Long id) {
		Department d = departments.findById(id).orElseThrow(() -> new NotFoundException("Department not found: " + id));
		return DepartmentMapper.toResponse(d);
	}

	@Override
	@Transactional(readOnly = true)
	public List<DepartmentResponse> list() {
		return departments.findAll().stream().map(DepartmentMapper::toResponse).toList();
	}

	@Override
	public DepartmentResponse update(Long id, DepartmentUpdateRequest request) {
		Department d = departments.findById(id).orElseThrow(() -> new NotFoundException("Department not found: " + id));
		DepartmentMapper.applyUpdate(request, d);
		return DepartmentMapper.toResponse(departments.save(d));
	}

	@Override
	public void delete(Long id) {
		Department d = departments.findById(id).orElseThrow(() -> new NotFoundException("Department not found: " + id));
		if (!d.getEmployees().isEmpty()) {
			throw new IllegalStateException(
					"Cannot delete department with employees; reassign or delete employees first.");
		}
		departments.delete(d);
	}
}
