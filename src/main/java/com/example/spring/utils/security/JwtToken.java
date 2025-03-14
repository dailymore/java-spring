package com.example.spring.utils.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.example.spring.utils.dto.response.StudentDto;
import com.example.spring.utils.dto.response.TeacherDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtToken {
	private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("mySuperSecretKeyThatIsLongEnoughForHS256".getBytes());
	private final Long EXPIRATION_TIME = 1000 * 60 * 60 * 24L; // 1 ngày
	private final ObjectMapper objectMapper = new ObjectMapper();

	private final Map<String, Class<?>> mapClass = new HashMap<String, Class<?>>() {
		{
			put(StudentDto.class.getSimpleName(), StudentDto.class);
			put(TeacherDto.class.getSimpleName(), TeacherDto.class);
		}
	};

	public <T> Map<String, String> generateToken(T instance) throws JsonProcessingException {
		String jwt = Jwts.builder()
				.subject(instance.getClass().getSimpleName())
				.claim("instance", instance)
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SECRET_KEY)
				.compact();

		return Map.of("accessToken", jwt);
	}

	public Object verifyToken(String Token) {
		try {
			Map<String, Object> claims = Jwts.parser()
					.verifyWith(SECRET_KEY)
					.build()
					.parseSignedClaims(Token)
					.getPayload();

			String className = (String) claims.get("sub");

			return objectMapper.convertValue(claims.get("instance"), mapClass.get(className));

		} catch (ExpiredJwtException e) {
			System.out.println("Token đã hết hạn!");

			return false;
		} catch (JwtException e) {
			System.out.println("Token không hợp lệ!");

			return false;
		}
	}
}
