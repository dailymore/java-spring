package com.example.spring.utils.dto.response;

import com.example.spring.student.StudentEntity;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentResponseDto {
	protected Long id;
	protected Integer age;
	protected String name;
	protected String studentId;
	protected String address;
	protected Float cpa;

	public StudentResponseDto(StudentEntity studentEntity) {
		this.id = studentEntity.getId();
		this.age = studentEntity.getAge();
		this.name = studentEntity.getName();
		this.studentId = studentEntity.getStudentId();
		this.address = studentEntity.getAddress();
		this.cpa = studentEntity.getCpa();
	}
}
