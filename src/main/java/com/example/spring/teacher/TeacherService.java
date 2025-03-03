package com.example.spring.teacher;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.teacher.repository.TeacherRepository;

@Service
public class TeacherService {
	@Autowired
	TeacherRepository teacherRepository;

	Optional<TeacherEntity> getOneTeacher(long id) {
		return this.teacherRepository.findById(id);
	}
}
