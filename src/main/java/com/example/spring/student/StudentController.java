package com.example.spring.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
public class StudentController {
	@Autowired
	private StudentService studentService;

	@GetMapping()
	List<StudentEntity> getAllProducts() {

		return studentService.getAllProducts();
	}
}
