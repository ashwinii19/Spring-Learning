package com.aurionpro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.dto.EmployeeRequestDTO;
import com.aurionpro.dto.EmployeeResponseDTO;
import com.aurionpro.entity.Employee;
import com.aurionpro.entity.Passport;
import com.aurionpro.exception.ResourceNotFoundException;
import com.aurionpro.mapper.EmployeeMapper;
import com.aurionpro.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{
	
	
	private EmployeeRepository repository;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository repository) {
		this.repository=repository;
	}

	@Override
	public EmployeeResponseDTO createEmployee(EmployeeRequestDTO requestDTO) {
		Employee employee=EmployeeMapper.toEntity(requestDTO);
		Employee saved=repository.save(employee);
		return EmployeeMapper.toResponse(saved);
		
	}

	@Override
	public EmployeeResponseDTO getEmployeeById(Long id) {
		Employee employee=repository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Employee not Found!"));
		return EmployeeMapper.toResponse(employee);
	}

	@Override
	public List<EmployeeResponseDTO> getAllEmployees() {
		
		List<Employee>employees=repository.findAll();
		
		return EmployeeMapper.toResponseList(employees);
		
	}

	@Override
	public EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO requestDTO) {
		Employee employee=repository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Employee not Found!"));
		
		employee.setFirstName(requestDTO.getFirstName());
		employee.setLastName(requestDTO.getLastName());
		employee.setEmail(requestDTO.getEmail());
	
		Passport passport=employee.getPassport();
		
		passport.setPassportNumber(requestDTO.getPassport().getPassportNumber());
		passport.setIssueDate(requestDTO.getPassport().getIssueDate());
        passport.setExpiryDate(requestDTO.getPassport().getExpiryDate());
        
        Employee updated=repository.save(employee);
        
        return EmployeeMapper.toResponse(updated);
	}

	@Override
	public void deleteEmployee(Long id) {
		Employee employee=repository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Employee not Found!"));
		
		repository.delete(employee);
	}

	
}
