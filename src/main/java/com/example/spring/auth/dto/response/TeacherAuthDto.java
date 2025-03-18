package com.example.spring.auth.dto.response;

import com.example.spring.teacher.TeacherEntity;
import com.example.spring.utils.dto.response.TeacherDto;
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
public class TeacherAuthDto extends TeacherDto {
	private String password;

	public TeacherAuthDto(TeacherEntity teacherEntity) {
		super(teacherEntity);
		this.password = teacherEntity.getPassword();
	}
}
