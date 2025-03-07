package com.example.spring.classroom;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.classroom.dto.request.ClassroomRequestDto;
import com.example.spring.classroom.dto.response.ClassroomResponseDto;
import com.example.spring.classroom.repository.ClassroomRepository;

@Service
public class ClassroomService {

	@Autowired
	private ClassroomRepository classroomRepository;

	public Optional<ClassroomResponseDto> getOneClassroom(ClassroomRequestDto classroomRequestDto) {
		return classroomRepository.findById(classroomRequestDto.getId())
				.map(classroom -> new ClassroomResponseDto(classroom));
	}
}