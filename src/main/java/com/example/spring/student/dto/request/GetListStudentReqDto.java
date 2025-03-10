package com.example.spring.student.dto.request;

import java.util.List;

import com.example.spring.utils.dto.ValidateRelationDto;
import com.example.spring.utils.dto.request.StudentRelationEnum;

public class GetListStudentReqDto extends ValidateRelationDto {

	public GetListStudentReqDto(List<String> relations) {
		super(relations);

		this.validate(StudentRelationEnum.class);
	}
}
