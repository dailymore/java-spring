package com.example.spring.classroom;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.classroom.dto.response.GetDetailClassroomDto;
import com.example.spring.classroom.dto.response.GetListClassroomDto;
import com.example.spring.classroom.repository.ClassroomRepository;

@Service
public class ClassroomService {

	@Autowired
	private ClassroomRepository classroomRepository;

	public Optional<GetDetailClassroomDto> getDetailClassroom(Long id) {
		return classroomRepository.findById(id).map(GetDetailClassroomDto::new);
	}

	public List<GetListClassroomDto> getListClassroom(List<String> relations) {
		return classroomRepository.findAll()
				.stream()
				.map(classroom -> new GetListClassroomDto(classroom, relations))
				.collect(Collectors.toList());
	}
}
