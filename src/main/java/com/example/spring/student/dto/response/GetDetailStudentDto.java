package com.example.spring.student.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.example.spring.student.StudentEntity;
import com.example.spring.utils.dto.response.ClassroomResponseDto;
import com.example.spring.utils.dto.response.StudentResponseDto;
import com.example.spring.utils.dto.response.TeacherResponseDto;

import lombok.Getter;

@Getter
public class GetDetailStudentDto extends StudentResponseDto {
	private ClassroomResponseDto classroom;
	private List<TeacherResponseDto> teacher;

	public GetDetailStudentDto(StudentEntity studentEntity) {
		super(studentEntity);

		this.classroom = new ClassroomResponseDto(studentEntity.getStudentClass());
		this.teacher = studentEntity.getStudentClass().getTeachers()
				.stream()
				.map(TeacherResponseDto::new)
				.collect(Collectors.toList());
	}

}
