package com.example.spring.teacher;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.teacher.dto.response.GetListTeacherDto;
import com.example.spring.teacher.dto.response.GetTeacherDetailDto;
import com.example.spring.teacher.repository.TeacherRepository;

@Service
public class TeacherService {
	@Autowired
	TeacherRepository teacherRepository;

	Optional<GetTeacherDetailDto> getOneTeacher(Long id) {
		return this.teacherRepository.findById(id).map(GetTeacherDetailDto::new);
	}

	List<GetListTeacherDto> getListTeacher(List<String> relations) {
		return this.teacherRepository.findAll()
				.stream()
				.map(teacher -> new GetListTeacherDto(teacher, relations))
				.collect(Collectors.toList());
	}
}
