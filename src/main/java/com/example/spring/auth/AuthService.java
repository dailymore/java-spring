package com.example.spring.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.student.repository.StudentRepository;
import com.example.spring.teacher.repository.TeacherRepository;

@Service
public class AuthService {
	@Autowired
	StudentRepository studentRepository;

	@Autowired
	TeacherRepository teacherRepository;

	public Boolean verifyAccount(String username, String password) {

		return false;

	}
}
