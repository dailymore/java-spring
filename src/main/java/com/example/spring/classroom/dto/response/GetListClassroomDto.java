package com.example.spring.classroom.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.example.spring.classroom.ClassroomEntity;
import com.example.spring.utils.dto.request.ClassroomRelationEnum;
import com.example.spring.utils.dto.response.ClassroomDto;
import com.example.spring.utils.dto.response.StudentDto;
import com.example.spring.utils.dto.response.TeacherDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetListClassroomDto extends ClassroomDto {
	List<TeacherDto> teachers;
	List<StudentDto> students;

	public GetListClassroomDto(ClassroomEntity classroomEntity) {
		this(classroomEntity, List.of());
	}

	public GetListClassroomDto(ClassroomEntity classroomEntity, List<String> relations) {
		super(classroomEntity);

		if (relations.contains(ClassroomRelationEnum.TEACHER.getValue()))
			this.teachers = classroomEntity
					.getTeachers()
					.stream()
					.map(TeacherDto::new)
					.collect(Collectors.toList());

		if (relations.contains(ClassroomRelationEnum.STUDENT.getValue()))
			this.students = classroomEntity
					.getStudents()
					.stream()
					.map(StudentDto::new)
					.collect(Collectors.toList());
	}
}
