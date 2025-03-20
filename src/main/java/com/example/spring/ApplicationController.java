package com.example.spring;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("public")
public class ApplicationController {
	@GetMapping("a")
	String helloSpringApp() {

		return "Hello ndmc will be pro";
	}

	@PostMapping()
	Object TestPostMapping(@RequestBody Map<String, String> code) {
		code.put("ndmc", "will be pro");

		return code;
	}
}
