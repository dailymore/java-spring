package com.example.spring.teacher;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("teacher")
public class TeacherController {

	@Autowired
	TeacherService teacherService;

	@GetMapping("/{id}")
	Optional<TeacherEntity> getOneTeacher(@PathVariable Long id) {
		return this.teacherService.getOneTeacher(id);
	}
}
