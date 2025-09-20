package com.aurionpro.service;

import com.aurionpro.dto.LoginDTO;
import com.aurionpro.dto.RegistrationDTO;
import com.aurionpro.dto.UserResponseDTO;

public interface AuthService {

	UserResponseDTO register(RegistrationDTO registration);
	
	String login(LoginDTO loginDTO);
}
