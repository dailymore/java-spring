package com.example.spring.teacher.dto.request;

import java.util.List;

import com.example.spring.utils.dto.Validate;
import com.example.spring.utils.dto.request.TeacherRelationEnum;

import lombok.Getter;

@Getter
public class GetListTeacherReqDto {
	private List<String> relations;

	public GetListTeacherReqDto(List<String> relations) {
		Validate.RelationsValidate(relations, TeacherRelationEnum.class);

		this.relations = relations;
	}
}
