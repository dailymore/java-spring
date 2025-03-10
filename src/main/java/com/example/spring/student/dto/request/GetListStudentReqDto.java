package com.example.spring.student.dto.request;

import java.util.List;

import com.example.spring.utils.dto.request.StudentRelationEnum;
import com.example.spring.utils.dto.request.Validate;

import lombok.Getter;

@Getter
public class GetListStudentReqDto {
	private List<String> relations;

	public GetListStudentReqDto(List<String> relations) {
		Validate.RelationsValidate(relations, StudentRelationEnum.class);

		this.relations = relations;
	}
}
