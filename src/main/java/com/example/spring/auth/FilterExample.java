package com.example.spring.auth;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterExample extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(
			@NonNull HttpServletRequest request,
			@NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain)
			throws ServletException, IOException {

		// * Decode để tránh trường hợp bị mã hoá thành %20 sẽ không split được
		// String authHeader = URLDecoder.decode(request.getHeader("Authorization"),
		// StandardCharsets.UTF_8);

		// jwtTokenService.verifyToken(authHeader);

		System.out.println("heello2222");

		filterChain.doFilter(request, response);
	}
}
