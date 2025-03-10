package com.example.spring.classroom.dto.request;

import java.util.List;

import com.example.spring.utils.dto.ValidateRelationDto;
import com.example.spring.utils.dto.request.ClassroomRelationEnum;

public class GetListClassroomReqDto extends ValidateRelationDto {

	public GetListClassroomReqDto(List<String> relations) {
		super(relations);

		this.validate(ClassroomRelationEnum.class);
	}
}
