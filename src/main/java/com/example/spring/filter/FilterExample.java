package com.example.spring.filter;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * ? Mặc định:
 * * khi có SecurityConfig, middleware custom luôn đi sau securityConfig
 */

// @Component
// * Đánh dấu là Bean để Spring có thể quản lý Filter này
public class FilterExample extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(
			@NonNull HttpServletRequest request,
			@NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain)
			throws ServletException, IOException {

		filterChain.doFilter(request, response);
	}
}
