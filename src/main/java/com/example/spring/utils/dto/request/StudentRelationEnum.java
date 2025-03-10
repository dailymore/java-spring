package com.example.spring.utils.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StudentRelationEnum {
	TEACHER("teacher"),
	CLASSROOM("classroom");

	private final String value;

}
