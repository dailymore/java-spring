package com.example.spring.teacher.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.example.spring.teacher.TeacherEntity;
import com.example.spring.utils.dto.response.ClassroomDto;
import com.example.spring.utils.dto.response.StudentDto;
import com.example.spring.utils.dto.response.TeacherDto;

import lombok.Getter;

@Getter
public class GetTeacherDetailDto extends TeacherDto {
	private List<ClassroomDto> classrooms;
	private List<StudentDto> students;

	public GetTeacherDetailDto(TeacherEntity teacherEntity) {
		super(teacherEntity);

		this.classrooms = teacherEntity.getClassrooms()
				.stream()
				.map(ClassroomDto::new)
				.collect(Collectors.toList());

		this.students = teacherEntity
				.getClassrooms()
				.stream()
				.flatMap(classroom -> classroom.getStudents().stream())
				.map(StudentDto::new)
				.collect(Collectors.toList());
	}

}
