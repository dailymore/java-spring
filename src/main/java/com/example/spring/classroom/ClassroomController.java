package com.example.spring.classroom;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.classroom.dto.request.ClassroomRequestDto;
import com.example.spring.classroom.dto.response.ClassroomResponseDto;

@RestController
@RequestMapping("classroom")
public class ClassroomController {
	@Autowired
	private ClassroomService classroomService;

	@GetMapping("/{id}")
	Optional<ClassroomResponseDto> getOneClassroom(@PathVariable long id) {
		ClassroomRequestDto classroomRequestDto = new ClassroomRequestDto(id);

		return this.classroomService.getOneClassroom(classroomRequestDto);
	}
}
