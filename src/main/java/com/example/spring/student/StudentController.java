package com.example.spring.student;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.student.dto.response.GetDetailStudentDto;

@RestController
@RequestMapping("student")
public class StudentController {
	@Autowired
	private StudentService studentService;

	@GetMapping("/{id}")
	Optional<GetDetailStudentDto> getDetailStudent(@PathVariable Long id) {
		return studentService.getDetailStudent(id);
	}

	@GetMapping()
	List<StudentEntity> getAllStudents() {
		return studentService.getAllStudents();
	}

}
