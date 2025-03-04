package com.example.spring.classroom;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.classroom.repository.ClassroomRepository;

@Service
public class ClassroomService {

	@Autowired
	private ClassroomRepository classroomRepository;

	Optional<ClassroomEntity> getOneClassroom(long id) {
		return this.classroomRepository.findById(id);
	}

}
