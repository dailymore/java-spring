package com.example.spring.utils.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtToken {
	private final Key SECRET_KEY;
	private final long EXPIRATION_TIME; // 1 ngày
	private final ObjectMapper objectMapper;

	public JwtToken() {
		this.objectMapper = new ObjectMapper();
		this.EXPIRATION_TIME = 86400000;
		this.SECRET_KEY = Keys.hmacShaKeyFor("mySuperSecretKeyThatIsLongEnoughForHS256".getBytes());
	}

	public final <T> String generateToken(T instance) throws JsonProcessingException {

		String jwt = Jwts.builder()
				.subject("authentication")
				.claim(instance.getClass().getSimpleName(), instance)
				.signWith(SECRET_KEY)
				.compact();

		return jwt;
	}

	public final String verifyToken(String Token) {

		return "hello";
	}

}
