package com.example.spring.utils.dto.response;

import java.util.List;

import com.example.spring.teacher.TeacherEntity;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeacherResponseDto {
	protected Long id;
	protected Integer age;
	protected String name;
	protected String university;
	protected String email;
	protected String phone;
	protected Integer experience;
	protected List<String> subject;

	public TeacherResponseDto(TeacherEntity teacherEntity) {
		this.id = teacherEntity.getId();
		this.age = teacherEntity.getAge();
		this.name = teacherEntity.getName();
		this.university = teacherEntity.getUniversity();
		this.email = teacherEntity.getEmail();
		this.phone = teacherEntity.getPhone();
		this.experience = teacherEntity.getExperience();
		this.subject = teacherEntity.getSubjects();
	}
}
