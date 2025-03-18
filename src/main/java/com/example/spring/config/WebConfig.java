package com.example.spring.config;

import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.spring.annotation.user.UserDetailResolver;

import io.micrometer.common.lang.NonNull;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addArgumentResolvers(@SuppressWarnings("null") @NonNull List<HandlerMethodArgumentResolver> resolvers) {
		/**
		 * ! Chú ý:
		 * * Không đánh dấu bean cho UserDetailResolver
		 * ? vì không cần thiết, chỉ có một nơi này dùng
		 * * => Tránh nặng Container
		 */
		resolvers.add(new UserDetailResolver());
	}
}
