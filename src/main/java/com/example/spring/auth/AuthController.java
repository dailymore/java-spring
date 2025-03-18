package com.example.spring.auth;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class AuthController {

	@Autowired
	AuthService authService;

	@PostMapping("login")
	Object Login(@RequestBody Map<String, String> body) {

		return authService.verifyAccount(body.get("username"), body.get("password"), body.get("type"));
	}
}
