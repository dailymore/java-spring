package com.example.spring.config.seed;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring.classroom.ClassroomEntity;
import com.example.spring.classroom.repository.ClassroomRepository;
import com.example.spring.student.StudentEntity;
import com.example.spring.student.repository.StudentRepository;
import com.example.spring.teacher.TeacherEntity;
import com.example.spring.teacher.repository.TeacherRepository;
import com.example.spring.utils.ArgonHash;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
class DataSeeder {
	List<TeacherEntity> teachers;
	List<ClassroomEntity> classrooms;
	List<StudentEntity> students;
}

@Component
public class Seeder implements CommandLineRunner {
	private String password;
	private ObjectMapper objectMapper;
	private InputStream inputStream;
	private DataSeeder dataSeeder;

	@Autowired
	private TeacherRepository teacherRepository;
	@Autowired
	private ClassroomRepository classroomRepository;
	@Autowired
	private StudentRepository studentRepository;

	public Seeder() throws StreamReadException, DatabindException, IOException {
		this.password = ArgonHash.hash("1234".toCharArray());
		this.objectMapper = new ObjectMapper();
		this.inputStream = new ClassPathResource("data.json").getInputStream();
		this.dataSeeder = this.objectMapper.readValue(inputStream, DataSeeder.class);
	}

	@Override
	public void run(String... args) throws Exception {
		if (teacherRepository.count() > 0 && classroomRepository.count() > 0 &&
				studentRepository.count() > 0)
			return;

		// add student in class , possition by index :))))) ( 1-n )
		dataSeeder.getStudents().get(0).setStudentClass(dataSeeder.getClassrooms().get(0));
		dataSeeder.getStudents().get(1).setStudentClass(dataSeeder.getClassrooms().get(1));
		dataSeeder.getStudents().get(2).setStudentClass(dataSeeder.getClassrooms().get(0));

		// add teacher in class ( m-m )
		dataSeeder.getClassrooms().get(0).setTeachers(List.of(dataSeeder.getTeachers().remove(0)));
		dataSeeder.getClassrooms().get(1).setTeachers(dataSeeder.getTeachers());

		classroomRepository.saveAll(dataSeeder.getClassrooms());
		teacherRepository.saveAll(dataSeeder.getTeachers());
		studentRepository.saveAll(dataSeeder.getStudents());

		seedData();
	}

	@Transactional
	void seedData() {
		System.out.println("run seed");
	}

}
