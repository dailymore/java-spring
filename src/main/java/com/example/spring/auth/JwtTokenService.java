package com.example.spring.auth;

import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class JwtTokenService {
	// * lấy từ env
	private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("thisIsMySuperSecretKeyWithAtLeast32Bytes!".getBytes());
	private final Long EXPIRATION_TIME = 1000 * 60 * 60 * 24L; // 1 ngày

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

	public Jwt decode(String jwtToken) {
		try {
			Jws<Claims> parsedJwt = Jwts.parser()
					.verifyWith(SECRET_KEY)
					.build()
					.parseSignedClaims(jwtToken);

			Claims claims = parsedJwt.getPayload();
			JwsHeader headers = parsedJwt.getHeader();

			Jwt jwt = Jwt.withTokenValue(jwtToken)
					.headers(h -> h.putAll(headers))
					.claims(c -> c.putAll(claims))
					.issuedAt(claims.getIssuedAt().toInstant())
					.expiresAt(claims.getExpiration().toInstant())
					.build();

			SecurityContextHolder.getContext().setAuthentication(new JwtAuthenticationToken(jwt));

			return jwt;
		} catch (ExpiredJwtException e) {
			System.out.println("Token đã hết hạn!\n" + e);

			return null;
		} catch (JwtException e) {
			System.out.println("Token không hợp lệ!\n" + e);

			return null;
		}
	}
}
