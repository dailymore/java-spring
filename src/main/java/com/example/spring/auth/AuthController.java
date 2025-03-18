package com.example.spring.auth;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.utils.dto.response.StudentDto;

@RestController
@RequestMapping()
public class AuthController {
	@Autowired
	JwtTokenService jwtTokenService;

	@PostMapping("login")
	Map<String, String> Login(@RequestBody Map<String, String> body) {

		StudentDto student = new StudentDto(
				1L,
				12,
				"ndmc",
				"20201111",
				"address",
				3.5F);

		return jwtTokenService.generateToken(student);
	}
}
