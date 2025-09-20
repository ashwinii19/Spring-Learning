package com.aurionpro.mapper;

import java.util.List;

import com.aurionpro.dto.EmployeeRequestDTO;
import com.aurionpro.dto.EmployeeResponseDTO;
import com.aurionpro.dto.PassportResponseDTO;
import com.aurionpro.entity.Employee;
import com.aurionpro.entity.Passport;

public class EmployeeMapper {

	public static Employee toEntity(EmployeeRequestDTO dto) {
		
		Employee employee=new Employee();
		
		employee.setFirstName(dto.getFirstName());
		employee.setLastName(dto.getLastName());
		employee.setEmail(dto.getEmail());
		
		Passport passport=new Passport();
		
		passport.setPassportNumber(dto.getPassport().getPassportNumber());
		passport.setIssueDate(dto.getPassport().getIssueDate());
		passport.setExpiryDate(dto.getPassport().getExpiryDate());
		
		employee.setPassport(passport);
		
		return employee;
	}
	
	public static EmployeeResponseDTO toResponse(Employee employee) {
		EmployeeResponseDTO dto=new EmployeeResponseDTO();
		dto.setId(employee.getId());
		dto.setFirstName(employee.getFirstName());
		dto.setLastName(employee.getLastName());
        dto.setEmail(employee.getEmail());

        PassportResponseDTO passportDTO = new PassportResponseDTO();
        passportDTO.setPassportNumber(employee.getPassport().getPassportNumber());
        passportDTO.setIssueDate(employee.getPassport().getIssueDate());
        passportDTO.setExpiryDate(employee.getPassport().getExpiryDate());
        
        dto.setPassport(passportDTO);

        return dto;
	}
	
	public static List<EmployeeResponseDTO>toResponseList(List<Employee>employees){
		return employees.stream()
						.map(emp->EmployeeMapper.toResponse(emp))
						.toList();
	}
	
	// .map(EmployeeMapper::toResponse);
	
	
	
	
}
