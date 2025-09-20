package com.aurionpro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class JwtAuthResponseDTO {

	private String accessToken;
	
	private String tokenType="Bearer";
}
