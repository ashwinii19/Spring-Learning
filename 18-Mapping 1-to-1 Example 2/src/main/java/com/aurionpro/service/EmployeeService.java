package com.aurionpro.service;

import java.util.List;

import com.aurionpro.dto.EmployeeRequestDTO;
import com.aurionpro.dto.EmployeeResponseDTO;

public interface EmployeeService {

	public EmployeeResponseDTO createEmployee(EmployeeRequestDTO requestDTO);
	public EmployeeResponseDTO getEmployeeById(Long id);
	public List<EmployeeResponseDTO>getAllEmployees();
	public EmployeeResponseDTO updateEmployee(Long id,EmployeeRequestDTO requestDTO);
	public void deleteEmployee(Long id);
}
