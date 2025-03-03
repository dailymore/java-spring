package com.example.spring.utils;

import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ObjectMapperUtil {

	private static final ObjectMapper objectMapper = new ObjectMapper(); // Static instance

	// Convert List<?> thành JSON
	public static String convertToJson(List<?> subjects) {
		try {
			return objectMapper.writeValueAsString(subjects);
		} catch (JsonProcessingException e) {
			e.printStackTrace();

			return "[]";
		}
	}


}
