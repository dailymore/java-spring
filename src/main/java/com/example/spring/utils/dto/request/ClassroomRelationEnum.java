package com.example.spring.utils.dto.request;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ClassroomRelationEnum {
	TEACHER("teacher"),
	STUDENT("student");

	private final String value;

	public static boolean includes(String value) {
		return Arrays
				.stream(ClassroomRelationEnum.values())
				.anyMatch(e -> e.name().equalsIgnoreCase(value));
	}
}
