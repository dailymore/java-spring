package com.example.spring.teacher.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.example.spring.teacher.TeacherEntity;
import com.example.spring.utils.dto.response.ClassroomResponseDto;
import com.example.spring.utils.dto.response.StudentResponseDto;
import com.example.spring.utils.dto.response.TeacherResponseDto;

import lombok.Getter;

@Getter
public class GetTeacherDetailDto extends TeacherResponseDto {
	private List<ClassroomResponseDto> classrooms;
	private List<StudentResponseDto> students;

	public GetTeacherDetailDto(TeacherEntity teacherEntity) {
		super(teacherEntity);

		this.classrooms = teacherEntity.getClassrooms()
				.stream()
				.map(ClassroomResponseDto::new)
				.collect(Collectors.toList());

		this.students = teacherEntity
				.getClassrooms()
				.stream()
				.flatMap(classroom -> classroom.getStudents().stream())
				.map(StudentResponseDto::new)
				.collect(Collectors.toList());
	}

}
