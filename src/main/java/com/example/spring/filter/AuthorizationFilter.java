package com.example.spring.filter;

import java.io.IOException;
import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * ? Mặc định:
 * * khi có SecurityConfig, middleware custom luôn đi sau securityConfig
 */

@Component
public class AuthorizationFilter extends OncePerRequestFilter {

	// @Override
	protected void doFilterInternal(
			@NonNull HttpServletRequest request,
			@NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain)
			throws ServletException, IOException {
		// * Khi Decode ở SecurityConfig nó sẽ trả về Jwt và được lưu ở trong Context
		// * Nhưng sẽ chỉ có token, không có authorities

		var authentication = SecurityContextHolder.getContext().getAuthentication();

		// * Bỏ qua nếu không phải là route authen (route nào auth thì mới có Jwt)
		if (!(authentication instanceof JwtAuthenticationToken)) {
			filterChain.doFilter(request, response);

			return;
		}

		var jwtAuth = (JwtAuthenticationToken) authentication;

		/**
		 * ? Set lại Authentication khi đã được set một lần ở SecurityConfig
		 * * Lần này sẽ set cùng với authorities
		 */
		var authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));

		SecurityContextHolder.getContext()
				.setAuthentication(new JwtAuthenticationToken(jwtAuth.getToken(), authorities));

		filterChain.doFilter(request, response);
	}
}
