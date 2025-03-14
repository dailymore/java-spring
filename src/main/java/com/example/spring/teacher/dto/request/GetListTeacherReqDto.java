package com.example.spring.teacher.dto.request;

import java.util.List;

import com.example.spring.utils.dto.ValidateRelationDto;
import com.example.spring.utils.dto.request.TeacherRelationEnum;

public class GetListTeacherReqDto extends ValidateRelationDto {

	public GetListTeacherReqDto(List<String> relations) {
		super(relations, TeacherRelationEnum.class);
	}
}
