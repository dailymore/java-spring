package com.example.spring.student.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.example.spring.student.StudentEntity;
import com.example.spring.utils.dto.response.ClassroomDto;
import com.example.spring.utils.dto.response.StudentDto;
import com.example.spring.utils.dto.response.TeacherDto;

import lombok.Getter;

@Getter
public class GetDetailStudentDto extends StudentDto {
	private ClassroomDto classroom;
	private List<TeacherDto> teacher;

	public GetDetailStudentDto(StudentEntity studentEntity) {
		super(studentEntity);

		this.classroom = new ClassroomDto(studentEntity.getStudentClass());
		this.teacher = studentEntity.getStudentClass().getTeachers()
				.stream()
				.map(TeacherDto::new)
				.collect(Collectors.toList());
	}

}
