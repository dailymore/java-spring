package com.example.spring.teacher;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.annotation.user.UserDetail;
import com.example.spring.teacher.dto.request.GetListTeacherReqDto;
import com.example.spring.teacher.dto.response.GetListTeacherDto;
import com.example.spring.teacher.dto.response.GetTeacherDetailDto;
import com.example.spring.utils.dto.response.StudentDto;

@RestController
@RequestMapping("teacher")
public class TeacherController {

	@Autowired
	TeacherService teacherService;

	@GetMapping("/{id}")
	Optional<GetTeacherDetailDto> getOneTeacher(@PathVariable Long id) {
		return this.teacherService.getOneTeacher(id);
	}

	@GetMapping()
	List<GetListTeacherDto> getListTeacher(
			@RequestParam(defaultValue = "") List<String> relations,
			@UserDetail StudentDto student) {

		var auth = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getAuthorities());
		System.out.println(student);

		return this.teacherService.getListTeacher(new GetListTeacherReqDto(relations).getRelations());
	}
}
