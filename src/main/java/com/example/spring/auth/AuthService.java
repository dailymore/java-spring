package com.example.spring.auth;

import static com.example.spring.utils.security.ArgonHash.verify;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.student.StudentEntity;
import com.example.spring.student.repository.StudentRepository;
import com.example.spring.teacher.repository.TeacherRepository;
import com.example.spring.utils.dto.response.StudentDto;

@Service
public class AuthService {
	@Autowired
	StudentRepository studentRepository;

	@Autowired
	TeacherRepository teacherRepository;

	@Autowired
	JwtTokenService jwtTokenService;

	public Object verifyAccount(String username, String password, String type) {
		if (type.equals("student")) {
			Optional<StudentEntity> student = this.studentRepository.findOneByStudentId(username);
			if (student.isEmpty())
				throw new Error();

			verify(student.get().getPassword(), password.toCharArray());

			return this.jwtTokenService.generateToken(new StudentDto(student.get()));
		}

		if (type.equals("teacher")) {

		}

		return false;

	}
}
