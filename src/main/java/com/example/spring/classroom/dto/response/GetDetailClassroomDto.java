package com.example.spring.classroom.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.example.spring.classroom.ClassroomEntity;
import com.example.spring.utils.dto.response.ClassroomDto;
import com.example.spring.utils.dto.response.StudentDto;
import com.example.spring.utils.dto.response.TeacherDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetDetailClassroomDto extends ClassroomDto {
	List<TeacherDto> teachers;
	List<StudentDto> students;

	/*
	 * Viết các trường nguyên thủy ở trong utils để dùng chung
	 * Các trường dành cho quan hệ thì định nghĩa riêng trong từng dto response
	 * Nếu không dùng trường nào thì chỉ cần viết setter là `null`
	 */

	public GetDetailClassroomDto(ClassroomEntity classroomEntity) {
		super(classroomEntity);

		this.teachers = classroomEntity
				.getTeachers()
				.stream()
				.map(teacher -> new TeacherDto(
						teacher.getId(),
						teacher.getAge(),
						teacher.getName(),
						teacher.getUniversity(),
						teacher.getEmail(),
						null,
						teacher.getExperience(),
						teacher.getSubjects()))
				.collect(Collectors.toList());

		this.students = classroomEntity
				.getStudents()
				.stream()
				.map(StudentDto::new)
				.collect(Collectors.toList());
	}
}
