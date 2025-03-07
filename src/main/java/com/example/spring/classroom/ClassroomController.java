package com.example.spring.classroom;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.classroom.dto.request.GetDetailClassroomReqDto;
import com.example.spring.classroom.dto.response.GetDetailClassroomDto;

@RestController
@RequestMapping("classroom")
public class ClassroomController {
	@Autowired
	private ClassroomService classroomService;

	@GetMapping("/{id}")
	Optional<GetDetailClassroomDto> getDetailClassroom(@PathVariable Long id) {
		GetDetailClassroomReqDto classroomRequestDto = new GetDetailClassroomReqDto(id);

		return this.classroomService.getDetailClassroom(classroomRequestDto);
	}
}
