package com.example.spring.classroom.dto.response;

import com.example.spring.classroom.ClassroomEntity;
import com.example.spring.utils.dto.response.ClassroomResponseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetDetailClassroomDto extends ClassroomResponseDto {
	public GetDetailClassroomDto(ClassroomEntity classroomEntity) {
		super(classroomEntity);
	}
}
