package com.example.spring.student;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.student.dto.response.GetDetailStudentDto;
import com.example.spring.student.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	public Optional<GetDetailStudentDto> getDetailStudent(Long id) {
		return this.studentRepository.findById(id).map(GetDetailStudentDto::new);
	}

	public List<StudentEntity> getAllStudents() {
		return this.studentRepository.findAll();
	}

}
