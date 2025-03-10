package com.example.spring.student.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.example.spring.student.StudentEntity;
import com.example.spring.utils.dto.response.ClassroomResponseDto;
import com.example.spring.utils.dto.response.StudentResponseDto;
import com.example.spring.utils.dto.response.TeacherResponseDto;

import lombok.Getter;

@Getter
public class GetListStudentDto extends StudentResponseDto {
	private ClassroomResponseDto classroom;
	private List<TeacherResponseDto> teacher;

	public GetListStudentDto(StudentEntity studentEntity, List<String> relations) {
		super(studentEntity);

		if (relations.contains("classroom"))
			this.classroom = new ClassroomResponseDto(studentEntity.getStudentClass());

		if (relations.contains("teacher"))
			this.teacher = studentEntity
					.getStudentClass()
					.getTeachers()
					.stream()
					.map(TeacherResponseDto::new)
					.collect(Collectors.toList());

	}

}
