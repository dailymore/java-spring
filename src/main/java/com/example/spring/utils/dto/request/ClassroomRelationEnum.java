package com.example.spring.utils.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ClassroomRelationEnum {
	TEACHER("teacher"),
	STUDENT("student");

	private final String value;

}
