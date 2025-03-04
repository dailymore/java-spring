package com.example.spring.classroom;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("classroom")
public class ClassroomController {
	@Autowired
	private ClassroomService classroomService;

	@GetMapping("/{id}")
	Optional<ClassroomEntity> getOneClassroom(@PathVariable long id) {
		return this.classroomService.getOneClassroom(id);
	}
}
