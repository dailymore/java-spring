package com.example.spring.utils.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.example.spring.classroom.ClassroomEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomResponseDto {
	private Long id;
	private String name;
	private String code;
	private String description;
	private int size;
	private List<TeacherResponseDto> teachers;

	public ClassroomResponseDto(ClassroomEntity classroomEntity) {
		this.id = classroomEntity.getId();
		this.name = classroomEntity.getName();
		this.code = classroomEntity.getCode();
		this.description = classroomEntity.getDescription();
		this.size = classroomEntity.getSize();

		this.teachers = classroomEntity.getTeachers()
				.stream()
				.map(TeacherResponseDto::new)
				// .map(teacher -> new TeacherResponseDto(teacher))
				.collect(Collectors.toList());
	}
}
