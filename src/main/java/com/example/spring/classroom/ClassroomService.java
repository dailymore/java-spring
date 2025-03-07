package com.example.spring.classroom;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.classroom.dto.request.GetDetailClassroomReqDto;
import com.example.spring.classroom.dto.response.GetDetailClassroomDto;
import com.example.spring.classroom.repository.ClassroomRepository;

@Service
public class ClassroomService {

	@Autowired
	private ClassroomRepository classroomRepository;

	public Optional<GetDetailClassroomDto> getDetailClassroom(GetDetailClassroomReqDto classroomRequestDto) {
		return classroomRepository.findById(classroomRequestDto.getId()).map(GetDetailClassroomDto::new);
	}
}
