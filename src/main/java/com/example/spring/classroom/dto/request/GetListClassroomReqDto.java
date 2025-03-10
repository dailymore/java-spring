package com.example.spring.classroom.dto.request;

import java.util.List;

import com.example.spring.utils.dto.request.ClassroomRelationEnum;

import lombok.Data;

@Data
public class GetListClassroomReqDto {
	private List<String> relations;

	public GetListClassroomReqDto(List<String> relations) {

		if (!relations.stream().allMatch(ClassroomRelationEnum::includes)) {
			throw new IllegalArgumentException("Relations không hợp lệ: " + relations);
		}
		/*
		 * relations.forEach(relation -> {
		 * if (!ClassroomRelationEnum.includes(relation))
		 * throw new IllegalArgumentException("Relations không hợp lệ: " + relation);
		 * });
		 */

		this.relations = relations;
	}
}
