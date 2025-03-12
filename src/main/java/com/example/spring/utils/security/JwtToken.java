package com.example.spring.utils.security;

import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public final class JwtToken {
	private final String SECRET_KEY = "hehe";
	private final long EXPIRATION_TIME = 86400000; // 1 ngày
	private final ObjectMapper objectMapper = new ObjectMapper();

	public final <T> String generateToken(T instance) {

		 Jwts.builder().subject(SECRET_KEY);
		//  https://github.com/jwtk/jjwt
		return "hello";
	}

	public final String verifyToken(String Token) {

		return "hello";
	}

}
