package com.example.spring.config.seed;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
import com.example.spring.utils.security.ArgonHash;
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
		if (teacherRepository.count() > 0 && classroomRepository.count() > 0 && studentRepository.count() > 0)
			return;

		seedData();
	}

	@Transactional
	void seedData() {
		/**
		 * ! Quy tắc lưu khi có relations.
		 ** 1. Luôn lưu các entity "độc lập" trước, không bị phụ thuộc (TeacherEntity)
		 ** 2. Luôn lưu entity "cha" trước khi liên kết với entity "con"
		 ** 3. Không gán entity chưa lưu vào một entity khác
		 */

		// todo: Cập nhật mật khẩu cho tất cả giáo viên & học sinh
		dataSeeder.getStudents().forEach(student -> student.setPassword(this.password));
		dataSeeder.getTeachers().forEach(teacher -> teacher.setPassword(this.password));

		// * Lưu bảng không bị phụ thuộc trước
		teacherRepository.saveAll(dataSeeder.getTeachers());

		// todo: Liên kết học sinh với lớp học
		dataSeeder.getStudents().get(0).setStudentClass(dataSeeder.getClassrooms().get(0));
		dataSeeder.getStudents().get(1).setStudentClass(dataSeeder.getClassrooms().get(1));
		dataSeeder.getStudents().get(2).setStudentClass(dataSeeder.getClassrooms().get(0));

		// todo: Liên kết giáo viên với lớp học
		dataSeeder.getClassrooms().get(1).setTeachers(dataSeeder.getTeachers());
		dataSeeder.getClassrooms().get(0).setTeachers(List.of(dataSeeder.getTeachers().get(0)));

		classroomRepository.saveAll(dataSeeder.getClassrooms());
		studentRepository.saveAll(dataSeeder.getStudents());

		/**
		 * ! Tránh dùng CascadeType.ALL
		 ** update bảng cha => bảng con sẽ ảnh hưởng
		 ** => dữ liệu biến đổi ảo diệu
		 ** => khó kiểm soát
		 */
	}
}
