package com.example.spring.auth.dto.response;

import com.example.spring.student.StudentEntity;
import com.example.spring.utils.dto.response.StudentDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentAuthDto extends StudentDto {
	private String password;

	public StudentAuthDto(StudentEntity studentEntity) {
		super(studentEntity);
		this.password = studentEntity.getPassword();
	}
}
