package com.aurionpro.app.mapper;

import com.aurionpro.app.dto.EmployeeResponse;
import com.aurionpro.app.dto.EmployeeUpdateRequest;
import com.aurionpro.app.entity.Department;
import com.aurionpro.app.entity.Employee;

public final class EmployeeMapper {
	private EmployeeMapper() {
	}

	public static EmployeeResponse toResponse(Employee e) {
		EmployeeResponse r = new EmployeeResponse();
		r.setId(e.getId());
		r.setFullName(e.getFullName());
		r.setSalary(e.getSalary());
		r.setDoj(e.getDoj());
		Department d = e.getDepartment();
		if (d != null) {
			r.setDepartmentId(d.getId());
			r.setDepartmentName(d.getName());
		}
		return r;
	}

	public static void applyUpdate(EmployeeUpdateRequest req, Employee e, Department newDeptIfAny) {
		if (req.getFullName() != null)
			e.setFullName(req.getFullName());
		if (req.getSalary() != null)
			e.setSalary(req.getSalary());
		if (req.getDoj() != null)
			e.setDoj(req.getDoj());
		if (newDeptIfAny != null) {
			// detach from old department list (if present) and attach to new
			Department old = e.getDepartment();
			if (old != null)
				old.getEmployees().remove(e);
			newDeptIfAny.addEmployee(e);
		}
	}
}
