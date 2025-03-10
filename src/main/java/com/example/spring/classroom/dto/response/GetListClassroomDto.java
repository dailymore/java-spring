package com.example.spring.classroom.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.example.spring.classroom.ClassroomEntity;
import com.example.spring.utils.dto.request.ClassroomRelationEnum;
import com.example.spring.utils.dto.response.ClassroomResponseDto;
import com.example.spring.utils.dto.response.StudentResponseDto;
import com.example.spring.utils.dto.response.TeacherResponseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetListClassroomDto extends ClassroomResponseDto {
	List<TeacherResponseDto> teachers;
	List<StudentResponseDto> students;

	public GetListClassroomDto(ClassroomEntity classroomEntity) {
		this(classroomEntity, List.of());
	}

	public GetListClassroomDto(ClassroomEntity classroomEntity, List<String> relations) {
		super(classroomEntity);

		if (relations.contains(ClassroomRelationEnum.TEACHER.getValue()))
			this.teachers = classroomEntity
					.getTeachers()
					.stream()
					.map(TeacherResponseDto::new)
					.collect(Collectors.toList());

		if (relations.contains(ClassroomRelationEnum.STUDENT.getValue()))
			this.students = classroomEntity
					.getStudents()
					.stream()
					.map(StudentResponseDto::new)
					.collect(Collectors.toList());
	}
}
