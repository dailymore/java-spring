package com.example.spring.utils.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TeacherRelationEnum {
	STUDENT("student"),
	CLASSROOM("classroom");

	private final String value;
}
