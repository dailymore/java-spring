package com.example.spring.utils.security;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtToken {
	private final Key SECRET_KEY = Keys.hmacShaKeyFor("mySuperSecretKeyThatIsLongEnoughForHS256".getBytes());
	private final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;
	private final ObjectMapper objectMapper;

	public JwtToken() {
		this.objectMapper = new ObjectMapper();
	}

	public final <T> Map<String, String> generateToken(T instance) throws JsonProcessingException {
		// Chuyển đổi object thành Map<String, Object> bằng ObjectMapper
		Map<String, Object> claimsMap = objectMapper.convertValue(instance, new TypeReference<Map<String, Object>>() {
		});
		// ? new TypeReference<Map<String, Object>>() {} = Map.class

		String jwt = Jwts.builder()
				.subject(instance.getClass().getSimpleName())
				.claims(claimsMap)
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SECRET_KEY)
				.compact();

		return Map.of("accessToken", jwt);
	}

	public final String verifyToken(String Token) {

		return "hello";
	}
}
