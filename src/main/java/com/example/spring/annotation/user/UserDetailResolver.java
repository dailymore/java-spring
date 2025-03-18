package com.example.spring.annotation.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.example.spring.auth.dto.response.StudentAuthDto;
import com.example.spring.auth.dto.response.TeacherAuthDto;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserDetailResolver implements HandlerMethodArgumentResolver {
	private ObjectMapper objectMapper = new ObjectMapper();
	private final Map<String, Class<?>> mapClass = new HashMap<String, Class<?>>() {
		{
			put(StudentAuthDto.class.getSimpleName(), StudentAuthDto.class);
			put(TeacherAuthDto.class.getSimpleName(), TeacherAuthDto.class);
		}
	};

	@Override
	public boolean supportsParameter(@NonNull MethodParameter parameter) {
		return parameter.hasParameterAnnotation(UserDetail.class);
	}

	@Override
	@Nullable
	public Object resolveArgument(
			@NonNull MethodParameter parameter,
			@Nullable ModelAndViewContainer mavContainer,
			@NonNull NativeWebRequest webRequest,
			@Nullable WebDataBinderFactory binderFactory) throws Exception {
		var auth = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

		Object instance = objectMapper.convertValue(
				auth.getTokenAttributes().get("instance"),
				mapClass.get(auth.getName()));

		return instance;
	}
}