package com.example.spring.classroom.dto.request;

import java.util.List;

import com.example.spring.utils.dto.Validate;
import com.example.spring.utils.dto.request.ClassroomRelationEnum;

import lombok.Data;

@Data
public class GetListClassroomReqDto {
	private List<String> relations;

	public GetListClassroomReqDto(List<String> relations) {
		Validate.RelationsValidate(relations, ClassroomRelationEnum.class);

		this.relations = relations;
	}
}
