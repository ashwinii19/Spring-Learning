package com.aurionpro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.dto.JwtAuthResponseDTO;
import com.aurionpro.dto.LoginDTO;
import com.aurionpro.dto.RegistrationDTO;
import com.aurionpro.dto.UserResponseDTO;
import com.aurionpro.service.AuthService;

@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired
	private AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<UserResponseDTO> register(@RequestBody RegistrationDTO registerDto)
	{
		return ResponseEntity.ok(authService.register(registerDto));
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponseDTO> login(@RequestBody LoginDTO loginDto)
	{
		
		String token=authService.login(loginDto);
		JwtAuthResponseDTO jwtAuthResponse=new JwtAuthResponseDTO();
		jwtAuthResponse.setAccessToken(token);
		
		return ResponseEntity.ok(jwtAuthResponse);
	}
}
