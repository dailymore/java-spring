package com.example.spring.utils.dto.response;

import java.util.List;

import com.example.spring.teacher.TeacherEntity;

import lombok.Data;

@Data
public class TeacherResponseDto {
	private Long id;
	private int age;
	private String name;
	private String university;
	private String email;
	private String phone;
	private int experience;
	private List<String> subject;
	// private List<ClassroomResponseDto> classrooms;

	public TeacherResponseDto(TeacherEntity teacherEntity) {
		this.id = teacherEntity.getId();
		this.age = teacherEntity.getAge();
		this.name = teacherEntity.getName();
		this.university = teacherEntity.getUniversity();
		this.email = teacherEntity.getEmail();
		this.phone = teacherEntity.getPhone();
		this.experience = teacherEntity.getExperience();
		this.subject = teacherEntity.getSubjects();

		// System.out.println(teacherEntity.getClassrooms());

		// .map(teacher -> new TeacherResponseDto(teacher))
		// .collect(Collectors.toList());
		;
	}
}
