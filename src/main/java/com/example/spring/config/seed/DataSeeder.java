package com.example.spring.config.seed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring.classroom.repository.ClassroomRepository;
import com.example.spring.student.repository.StudentRepository;
import com.example.spring.teacher.repository.TeacherRepository;
import com.example.spring.utils.ArgonHash;

@Component
public class DataSeeder implements CommandLineRunner {

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private ClassroomRepository classroomRepository;

	@Autowired
	private StudentRepository studentRepository;

	private String password = ArgonHash.hash("1234".toCharArray());

	@Override
	public void run(String... args) throws Exception {
		if (teacherRepository.count() > 0 && classroomRepository.count() > 0 && studentRepository.count() > 0)
			return;

		seedData();
	}

	@Transactional
	void seedData() {
		System.out.println("run seed");
	}
}
