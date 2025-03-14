package com.example.spring.teacher.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.example.spring.teacher.TeacherEntity;
import com.example.spring.utils.dto.request.TeacherRelationEnum;
import com.example.spring.utils.dto.response.ClassroomDto;
import com.example.spring.utils.dto.response.StudentDto;
import com.example.spring.utils.dto.response.TeacherDto;

import lombok.Getter;

@Getter
public class GetListTeacherDto extends TeacherDto {
	private List<ClassroomDto> classrooms;
	private List<StudentDto> students;

	public GetListTeacherDto(TeacherEntity teacherEntity, List<String> relations) {
		super(teacherEntity);

		if (relations.contains(TeacherRelationEnum.CLASSROOM.getValue()))
			this.classrooms = teacherEntity.getClassrooms()
					.stream()
					.map(ClassroomDto::new)
					.collect(Collectors.toList());

		if (relations.contains(TeacherRelationEnum.STUDENT.getValue()))
			this.students = teacherEntity
					.getClassrooms()
					.stream()
					.flatMap(classroom -> classroom.getStudents().stream())
					.map(StudentDto::new)
					.collect(Collectors.toList());
	}

}
