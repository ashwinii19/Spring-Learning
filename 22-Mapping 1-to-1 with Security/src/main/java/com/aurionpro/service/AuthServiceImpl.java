package com.aurionpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aurionpro.dto.LoginDTO;
import com.aurionpro.dto.RegistrationDTO;
import com.aurionpro.dto.UserResponseDTO;
import com.aurionpro.entity.Role;
import com.aurionpro.entity.User;
import com.aurionpro.exception.UserApiException;
import com.aurionpro.repository.RoleRepository;
import com.aurionpro.repository.UserRepository;
import com.aurionpro.security.JwtTokenProvider;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtTokenProvider tokenProvider;

	@Override
	public UserResponseDTO register(RegistrationDTO registration) {
		
		if (userRepo.existsByUsername(registration.getUsername()))
			throw new UserApiException(HttpStatus.BAD_REQUEST, "User already exists");

		User user = new User();
		user.setUsername(registration.getUsername());
		
		
		user.setPassword(passwordEncoder.encode(registration.getPassword()));
		
		Role userRole = roleRepo.findByRolename(registration.getRole())
				.orElseThrow(() -> new UserApiException(HttpStatus.BAD_REQUEST, "Role does not exists"));
		userRole.getUsers().add(user);
		user.setRole(userRole);
		
		user = userRepo.save(user);

		UserResponseDTO dto = new UserResponseDTO();
		dto.setUserId(user.getUserId());
		dto.setUsername(user.getUsername());

		return dto;
	}

	@Override
	public String login(LoginDTO loginDTO) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String token = tokenProvider.generateToken(authentication);

			return token;
		} catch (BadCredentialsException e) {
			throw new UserApiException(HttpStatus.NOT_FOUND, "Username or Password is incorrect");
		}
		
	}

}
