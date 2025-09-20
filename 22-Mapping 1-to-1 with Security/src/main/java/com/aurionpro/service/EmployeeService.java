package com.aurionpro.service;

import java.util.List;

import com.aurionpro.dto.EmployeeCreateDTO;
import com.aurionpro.dto.EmployeeResponseDTO;
import com.aurionpro.dto.EmployeeUpdateDTO;
import com.aurionpro.dto.PassportCreateDTO;
import com.aurionpro.dto.PassportUpdateDTO;

public interface EmployeeService {

	public EmployeeResponseDTO createEmployee(EmployeeCreateDTO requestDTO);
	public EmployeeResponseDTO getEmployeeById(Long id);
	public List<EmployeeResponseDTO>getAllEmployees();
	public EmployeeResponseDTO updateEmployee(Long id,EmployeeUpdateDTO requestDTO);
	public void deleteEmployee(Long id);
	
	public EmployeeResponseDTO addProfile(Long id,PassportCreateDTO passport);
	public EmployeeResponseDTO updateProfile(Long id,PassportUpdateDTO passport);
}
