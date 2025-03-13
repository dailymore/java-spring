package com.example.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.utils.dto.response.StudentResponseDto;
import com.example.spring.utils.security.JwtToken;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("public")
public class ApplicationController {
	@Autowired
	private JwtToken jwtToken;

	@GetMapping()
	String helloSpringApp() throws JsonProcessingException {

		StudentResponseDto student = new StudentResponseDto(
				1L,
				12,
				"ndmc",
				"20201111",
				"address",
				3.5F);

		return jwtToken.generateToken(student);
	}
}
