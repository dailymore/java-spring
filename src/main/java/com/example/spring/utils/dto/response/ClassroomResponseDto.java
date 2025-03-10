package com.example.spring.utils.dto.response;

import com.example.spring.classroom.ClassroomEntity;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClassroomResponseDto {
	// * Nên khai báo Wrapper class cho các kiểu dữ liệu trong Dto
	protected Long id;
	protected String name;
	protected String code;
	protected String description;
	protected Integer size;

	public ClassroomResponseDto(ClassroomEntity classroomEntity) {
		this.id = classroomEntity.getId();
		this.name = classroomEntity.getName();
		this.code = classroomEntity.getCode();
		this.description = classroomEntity.getDescription();
		this.size = classroomEntity.getSize();
	}
}
