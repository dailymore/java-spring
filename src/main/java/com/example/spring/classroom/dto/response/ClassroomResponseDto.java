package com.example.spring.classroom.dto.response;

import java.util.List;

import com.example.spring.classroom.ClassroomEntity;
import com.example.spring.teacher.TeacherEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClassroomResponseDto {
	private String name;
	private String code;
	private List<TeacherEntity> teachers;

	public ClassroomResponseDto(ClassroomEntity classroomEntity) {
		this.name = classroomEntity.getName();
		this.code = classroomEntity.getCode();
		this.teachers = classroomEntity.getTeachers();
	}
}
