package com.example.spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class ApplicationController {
	@GetMapping()
	String helloSpringApp() {
		return "Hello Spring App";
	}
}
