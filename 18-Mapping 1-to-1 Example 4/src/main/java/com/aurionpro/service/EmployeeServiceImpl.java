package com.aurionpro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.dto.EmployeeCreateDTO;
import com.aurionpro.dto.EmployeeResponseDTO;
import com.aurionpro.dto.EmployeeUpdateDTO;
import com.aurionpro.dto.PassportCreateDTO;
import com.aurionpro.dto.PassportUpdateDTO;
import com.aurionpro.entity.Employee;
import com.aurionpro.entity.Passport;
import com.aurionpro.exception.ResourceNotFoundException;
import com.aurionpro.mapper.EmployeeMapper;
import com.aurionpro.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository repository;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepository repository) {
		this.repository = repository;
	}

//	RequestDTO → Entity → Save → ResponseDTO
	@Override
	public EmployeeResponseDTO createEmployee(EmployeeCreateDTO requestDTO) {
		Employee employee = EmployeeMapper.toEntity(requestDTO);
		Employee saved = repository.save(employee);
		return EmployeeMapper.toResponse(saved);

	}

//	DB → Convert to DTO → Return
	@Override
	public EmployeeResponseDTO getEmployeeById(Long id) {
		Employee employee = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not Found!"));
		return EmployeeMapper.toResponse(employee);
	}

//	DB se sab record → Entity → DTO → List return
	@Override
	public List<EmployeeResponseDTO> getAllEmployees() {

		List<Employee> employees = repository.findAll();

		return EmployeeMapper.toResponseList(employees);

	}

//	Find → Update fields → Save → DTO return
	@Override
	public EmployeeResponseDTO updateEmployee(Long id, EmployeeUpdateDTO employeeUpdateDTO) {
		Employee employee = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not Found!"));
		EmployeeMapper.applyUpdate(employeeUpdateDTO, employee);

		Employee updated = repository.save(employee);

		return EmployeeMapper.toResponse(updated);
	}

//	Find → Delete Employee → Passport auto delete (cascade)
	@Override
	public void deleteEmployee(Long id) {
		Employee employee = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not Found!"));

		repository.delete(employee);
	}

//	Find Employee → Check Passport Exists → Create Passport → Link to Employee → Save → DTO return
	@Override
	public EmployeeResponseDTO addProfile(Long id, PassportCreateDTO passportDTO) {
		Employee employee = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not Found!"));
		if (employee.getPassport() != null) {
			throw new IllegalStateException("Passport Already Exits for Employee"+id);
			
		}
		Passport passport = new Passport();
		passport.setPassportNumber(passportDTO.getPassportNumber());
		passport.setIssueDate(passportDTO.getIssueDate());
		passport.setExpiryDate(passportDTO.getExpiryDate());
		
		employee.setPassport(passport);

		Employee updated = repository.save(employee);

		return EmployeeMapper.toResponse(updated);
	}

//	Find Employee → Check Passport Exists → Update Passport Fields → Save → DTO return
	@Override
	public EmployeeResponseDTO updateProfile(Long id, PassportUpdateDTO passportDTO) {
		Employee employee = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not Found!"));
		Passport passport = employee.getPassport();
		if (passport == null) {
			throw new ResourceNotFoundException("Passport not Found for Employee" + id);
		}
		if (passportDTO.getExpiryDate() != null) {
			passport.setExpiryDate(passportDTO.getExpiryDate());
		}
		employee.setPassport(passport);

		Employee updated = repository.save(employee);

		return EmployeeMapper.toResponse(updated);

	}

}
