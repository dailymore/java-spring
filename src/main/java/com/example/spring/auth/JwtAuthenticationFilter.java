package com.example.spring.auth;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.spring.utils.security.JwtTokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
	private JwtTokenService jwtTokenService;

	public JwtAuthenticationFilter(JwtTokenService jwtTokenService) {
		this.jwtTokenService = jwtTokenService;
	}

	@Override
	protected void doFilterInternal(
			@NonNull HttpServletRequest request,
			@NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain)
			throws ServletException, IOException {

		// * Decode để tránh trường hợp bị mã hoá dấu cách thành %20 sẽ không split được
		// String authHeader = URLDecoder.decode(request.getHeader("Authorization"),
		// StandardCharsets.UTF_8);

		// jwtTokenService.verifyToken(authHeader);

		System.out.println("heello");

		filterChain.doFilter(request, response);
	}
}
