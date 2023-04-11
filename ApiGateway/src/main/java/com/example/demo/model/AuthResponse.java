package com.example.demo.model;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
	
	private String userId;
	
	private String accessToken;
	
	private Long expireAt;
	
	private String refreshToken;
	
	private Collection<String> authorites;

}