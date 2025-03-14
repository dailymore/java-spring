package com.example.spring;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.utils.dto.response.StudentDto;
import com.example.spring.utils.security.JwtToken;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("public")
public class ApplicationController {
	@Autowired
	private JwtToken jwtToken;

	@GetMapping()
	Map<String, String> helloSpringApp() throws JsonProcessingException {

		StudentDto student = new StudentDto(
				1L,
				12,
				"ndmc",
				"20201111",
				"address",
				3.5F);

		return jwtToken.generateToken(student);
	}

	@PostMapping()
	Object verifyJwt(@RequestBody Map<String, String> code) {

		return jwtToken.verifyToken(code.get("accessToken"));
	}
}
