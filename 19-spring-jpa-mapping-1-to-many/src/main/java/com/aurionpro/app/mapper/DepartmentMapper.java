package com.aurionpro.app.mapper;

import java.util.List;

import com.aurionpro.app.dto.DepartmentCreateRequest;
import com.aurionpro.app.dto.DepartmentResponse;
import com.aurionpro.app.dto.DepartmentUpdateRequest;
import com.aurionpro.app.dto.EmployeeDto;
import com.aurionpro.app.entity.Department;
import com.aurionpro.app.entity.Employee;

public final class DepartmentMapper {
	private DepartmentMapper() {
	}

	public static DepartmentResponse toResponse(Department d) {
		DepartmentResponse r = new DepartmentResponse();
		r.setId(d.getId());
		r.setName(d.getName());
		r.setEmployees(toEmployeeDtos(d.getEmployees()));
		return r;
	}

	private static List<EmployeeDto> toEmployeeDtos(List<Employee> list) {
		if (list == null || list.isEmpty())
			return List.of();
		return list.stream().map(DepartmentMapper::toDto).toList();
	}

	private static EmployeeDto toDto(Employee e) {
		EmployeeDto d = new EmployeeDto();
		d.setId(e.getId());
		d.setFullName(e.getFullName());
		d.setSalary(e.getSalary());
		d.setDoj(e.getDoj());
		return d;
	}

	public static Department toNewEntity(DepartmentCreateRequest req) {
		Department d = new Department();
		d.setName(req.getName());
		return d;
	}

	public static void applyUpdate(DepartmentUpdateRequest req, Department d) {
		if (req.getName() != null)
			d.setName(req.getName());
	}
}
