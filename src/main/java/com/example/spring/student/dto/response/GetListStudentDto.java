package com.example.spring.student.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.example.spring.student.StudentEntity;
import com.example.spring.utils.dto.request.StudentRelationEnum;
import com.example.spring.utils.dto.response.ClassroomDto;
import com.example.spring.utils.dto.response.StudentDto;
import com.example.spring.utils.dto.response.TeacherDto;

import lombok.Getter;

@Getter
public class GetListStudentDto extends StudentDto {
	private ClassroomDto classroom;
	private List<TeacherDto> teacher;

	public GetListStudentDto(StudentEntity studentEntity) {
		this(studentEntity, List.of());
	}

	public GetListStudentDto(StudentEntity studentEntity, List<String> relations) {
		super(studentEntity);

		if (relations.contains(StudentRelationEnum.CLASSROOM.getValue()))
			this.classroom = new ClassroomDto(studentEntity.getStudentClass());

		if (relations.contains(StudentRelationEnum.TEACHER.getValue()))
			this.teacher = studentEntity
					.getStudentClass()
					.getTeachers()
					.stream()
					.map(TeacherDto::new)
					.collect(Collectors.toList());
	}

}
