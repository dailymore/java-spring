package com.example.spring.auth;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.oauth2.jwt.Jwt;

@Service
public class JwtTokenService {
	// * lấy từ env
	private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("thisIsMySuperSecretKeyWithAtLeast32Bytes!".getBytes());
	private final Long EXPIRATION_TIME = 1000 * 60 * 60 * 24L; // 1 ngày

	public <T> Map<String, String> generateToken(T instance) {
		String jwt = Jwts.builder()
				.subject(instance.getClass().getSimpleName())
				.claim("instance", instance)
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SECRET_KEY)
				.compact();

		return Map.of("accessToken", jwt);
	}

	/**
	 * ! Chú ý:
	 ** Hàm `Decode` này chỉ có nhiệm vụ `Decode` và trả về Jwt
	 ** Không nên có nhiệm vụ khác: vd `setAuthentication`
	 */
	public Jwt decode(String jwtToken) {
		System.out.println("helelo");

		try {
			Jws<Claims> parsedJwt = Jwts.parser()
					.verifyWith(SECRET_KEY)
					.build()
					.parseSignedClaims(jwtToken);

			Claims claims = parsedJwt.getPayload();
			JwsHeader headers = parsedJwt.getHeader();

			return Jwt.withTokenValue(jwtToken)
					.headers(h -> h.putAll(headers))
					.claims(c -> c.putAll(claims))
					.issuedAt(claims.getIssuedAt().toInstant())
					.expiresAt(claims.getExpiration().toInstant())
					.build();
			/**
			 * ! chú ý :
			 ** Sau khi Decode xong thì thằng SecurityConfig sẽ setAuthentication
			 ** lưu authentication ở đây thì bị ghi đè trong Request => mất authorities
			 ** Nếu vẫn dùng để lưu Jwt thì nên lưu lại sau khi nó đã set thành công (filter)
			 * 
			 * todo:
			 * => Không oce lắm, improve cách nào đỏ để chỉ cần set một lần
			 */
		} catch (ExpiredJwtException e) {
			System.out.println("Token đã hết hạn!\n" + e);

			return null;
		} catch (JwtException e) {
			System.out.println("Token không hợp lệ!\n" + e);

			return null;
		}
	}
}
