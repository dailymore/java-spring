package com.example.spring.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.student.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	public List<StudentEntity> getAllProducts() {
		return this.studentRepository.findAll();
	}

}
