package com.aurionpro.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aurionpro.app.dto.EmployeeCreateRequest;
import com.aurionpro.app.dto.EmployeeResponse;
import com.aurionpro.app.dto.EmployeeUpdateRequest;
import com.aurionpro.app.entity.Department;
import com.aurionpro.app.entity.Employee;
import com.aurionpro.app.exception.NotFoundException;
import com.aurionpro.app.mapper.EmployeeMapper;
import com.aurionpro.app.repository.DepartmentRepository;
import com.aurionpro.app.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employees;
	private final DepartmentRepository departments;

	public EmployeeServiceImpl(EmployeeRepository employees, DepartmentRepository departments) {
		this.employees = employees;
		this.departments = departments;
	}

	@Override
	public EmployeeResponse create(EmployeeCreateRequest req) {
		Department dept = departments.findById(req.getDepartmentId())
				.orElseThrow(() -> new NotFoundException("Department not found: " + req.getDepartmentId()));

		Employee e = new Employee();
		e.setFullName(req.getFullName());
		e.setSalary(req.getSalary());
		e.setDoj(req.getDoj());

		// attach to department (keep both sides consistent)
		dept.addEmployee(e);

		Employee saved = employees.save(e); // owning side is Employee
		return EmployeeMapper.toResponse(saved);
	}

	@Override
	@Transactional(readOnly = true)
	public EmployeeResponse get(Long id) {
		Employee e = employees.findById(id).orElseThrow(() -> new NotFoundException("Employee not found: " + id));
		return EmployeeMapper.toResponse(e);
	}

	@Override
	@Transactional(readOnly = true)
	public List<EmployeeResponse> list() {
		return employees.findAll().stream().map(EmployeeMapper::toResponse).toList();
	}

	@Override
	public EmployeeResponse update(Long id, EmployeeUpdateRequest req) {
		Employee e = employees.findById(id).orElseThrow(() -> new NotFoundException("Employee not found: " + id));

		Department newDept = null;
		if (req.getDepartmentId() != null) {
			newDept = departments.findById(req.getDepartmentId())
					.orElseThrow(() -> new NotFoundException("Department not found: " + req.getDepartmentId()));
		}

		EmployeeMapper.applyUpdate(req, e, newDept);
		return EmployeeMapper.toResponse(employees.save(e));
	}

	@Override
	public void delete(Long id) {
		if (!employees.existsById(id))
			throw new NotFoundException("Employee not found: " + id);
		employees.deleteById(id);
	}

	@Override
	public EmployeeResponse moveToDepartment(Long empId, Long newDeptId) {
		Employee e = employees.findById(empId).orElseThrow(() -> new NotFoundException("Employee not found: " + empId));
		Department newDept = departments.findById(newDeptId)
				.orElseThrow(() -> new NotFoundException("Department not found: " + newDeptId));

		Department old = e.getDepartment();
		if (old != null)
			old.getEmployees().remove(e);
		newDept.addEmployee(e); // sync both sides

		return EmployeeMapper.toResponse(employees.save(e));
	}
}
