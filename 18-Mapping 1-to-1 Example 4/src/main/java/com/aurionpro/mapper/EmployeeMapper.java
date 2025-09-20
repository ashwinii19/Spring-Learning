package com.aurionpro.mapper;

import java.util.List;

import com.aurionpro.dto.EmployeeCreateDTO;
import com.aurionpro.dto.EmployeeResponseDTO;
import com.aurionpro.dto.EmployeeUpdateDTO;
import com.aurionpro.dto.PassportCreateDTO;
import com.aurionpro.dto.PassportUpdateDTO;
import com.aurionpro.entity.Employee;
import com.aurionpro.entity.Passport;

public class EmployeeMapper {

//	DTO → Entity
	public static Employee toEntity(EmployeeCreateDTO dto) {
		
		Employee employee=new Employee();
		
		employee.setFirstName(dto.getFirstName());
		employee.setLastName(dto.getLastName());
		employee.setEmail(dto.getEmail());
		
		if(dto.getPassport()!=null){
			employee.setPassport(toPassport(dto.getPassport()));
		}
		
		
		return employee;
	}
	
	
	
	private static Passport toPassport(PassportCreateDTO passportCreateDTO) {
		Passport passport=new Passport();
		passport.setPassportNumber(passportCreateDTO.getPassportNumber());
		passport.setIssueDate(passportCreateDTO.getIssueDate());
		passport.setExpiryDate(passportCreateDTO.getExpiryDate());
		
		return passport;
	}
	
	
//	Entity → DTO
	public static EmployeeResponseDTO toResponse(Employee employee) {
		EmployeeResponseDTO dto=new EmployeeResponseDTO();
		dto.setId(employee.getId());
		dto.setFirstName(employee.getFirstName());
		dto.setLastName(employee.getLastName());
        dto.setEmail(employee.getEmail());

        
        dto.setPassport(passportCreateDTO(employee.getPassport()));

        return dto;
	}
	
	
	private static PassportCreateDTO passportCreateDTO(Passport passport) {
		if(passport==null) {
			return null;
		}
		PassportCreateDTO passportCreateDTO=new PassportCreateDTO();
		passportCreateDTO.setPassportNumber(passport.getPassportNumber());
		passportCreateDTO.setIssueDate(passport.getIssueDate());
		passportCreateDTO.setExpiryDate(passport.getExpiryDate());
		
		return passportCreateDTO;
		
	}

	
	public static List<EmployeeResponseDTO>toResponseList(List<Employee>employees){
		return employees.stream()
						.map(emp->EmployeeMapper.toResponse(emp))
						.toList();
	}
	
	// .map(EmployeeMapper::toResponse);
	
	
	public static void applyUpdate(EmployeeUpdateDTO dto, Employee employee) {
		if (dto.getFirstName() != null)
			employee.setFirstName(dto.getFirstName());
		if (dto.getLastName() != null)
			employee.setLastName(dto.getLastName());
		if (dto.getEmail() != null)
			employee.setEmail(dto.getEmail());
		if (dto.getPassport() != null) {
			Passport passport = employee.getPassport();
			if (passport == null)
				passport = new Passport();
			PassportUpdateDTO passportUpdateDTO = dto.getPassport();
			if (passportUpdateDTO.getExpiryDate() != null)
				passport.setExpiryDate(passportUpdateDTO.getExpiryDate());
			
			employee.setPassport(passport);
		}
	}
	
	
	
	
}
