package com.example.spring.auth;

import static com.example.spring.utils.security.ArgonHash.verify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.auth.dto.response.StudentAuthDto;
import com.example.spring.auth.dto.response.TeacherAuthDto;
import com.example.spring.student.repository.StudentRepository;
import com.example.spring.teacher.repository.TeacherRepository;

@Service
public class AuthService {
	@Autowired
	StudentRepository studentRepository;

	@Autowired
	TeacherRepository teacherRepo;

	@Autowired
	JwtTokenService jwtTokenService;

	public Object verifyAccount(String username, String password, String type) {
		if (type.equals("student")) {
			var student = this.studentRepository.findOneByStudentId(username).map(StudentAuthDto::new);

			if (student.isEmpty())
				throw new Error();

			verify(student.get().getPassword(), password.toCharArray());
			student.get().setPassword(null);

			return this.jwtTokenService.generateToken(student.get());
		}

		if (type.equals("teacher")) {
			var teacher = this.teacherRepo.findOneByEmailOrPhone(username).map(TeacherAuthDto::new);

			if (teacher.isEmpty())
				throw new Error();

			verify(teacher.get().getPassword(), password.toCharArray());
			teacher.get().setPassword(null);

			return this.jwtTokenService.generateToken(teacher.get());
		}

		throw new Error();

	}
}
