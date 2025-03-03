package com.example.spring.student;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.student.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	public List<StudentEntity> getAllStudents() {
		return this.studentRepository.findAll();
	}

	public Optional<StudentEntity> getOneStudent(long id) {

		return this.studentRepository.findById(id);
	}
}
