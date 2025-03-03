package com.example.spring.config;

import com.example.spring.classroom.ClassroomEntity;
import com.example.spring.classroom.repository.ClassroomRepository;
import com.example.spring.student.StudentEntity;
import com.example.spring.student.repository.StudentRepository;
import com.example.spring.teacher.TeacherEntity;
import com.example.spring.teacher.repository.TeacherRepository;
import com.example.spring.utils.ArgonHash;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@EnableTransactionManagement
public class DataSeeder {

	private String password = ArgonHash.hash("1234".toCharArray());

	@Bean
	CommandLineRunner initData(
			TeacherRepository teacherRepository,
			ClassroomRepository classroomRepository,
			StudentRepository studentRepository) {

		return args -> {
			if (teacherRepository.count() > 0 && classroomRepository.count() > 0 && studentRepository.count() > 0)
				return;

			seedData(teacherRepository, classroomRepository, studentRepository);
		};
	}

	@Transactional
	void seedData(
			TeacherRepository teacherRepository,
			ClassroomRepository classroomRepository,
			StudentRepository studentRepository) {

		try {
			ObjectMapper objectMapper = new ObjectMapper();

			// 🏫 Tạo danh sách lớp học
			ClassroomEntity classA = new ClassroomEntity(null, "Class A", "CLS-A", "Lớp học lập trình", 30, null, null);
			ClassroomEntity classB = new ClassroomEntity(null, "Class B", "CLS-B", "Lớp học toán học", 25, null, null);
			classA.setTeachers(new ArrayList<>());
			classB.setTeachers(new ArrayList<>());

			// 👨‍🏫 Tạo danh sách giáo viên
			TeacherEntity teacher1 = new TeacherEntity(
					null, "Nguyễn Văn A", "ĐH Bách Khoa", 40, 15, "0123456789",
					"a@gmail.com", objectMapper.writeValueAsString(List.of("Math", "Physics")), this.password, List.of());
			TeacherEntity teacher2 = new TeacherEntity(
					null, "Trần Thị B", "ĐH Quốc Gia", 35, 10, "0987654321",
					"b@gmail.com", objectMapper.writeValueAsString(List.of("Programming", "AI")), this.password, List.of());

			// 🧑‍🎓 Tạo danh sách sinh viên
			StudentEntity student1 = new StudentEntity(null, "Lê Hồng Đức", "S001", "Class A", "Hà Nội", 20, 3.5f,
					"pass123", classA);
			StudentEntity student2 = new StudentEntity(null, "Nguyễn Hoàng Anh", "S002", "Class A", "HCM", 21, 3.8f,
					"pass123", classA);
			StudentEntity student3 = new StudentEntity(null, "Phạm Văn Cường", "S003", "Class B", "Đà Nẵng", 22, 3.2f,
					"pass123", classB);

			// Liên kết nhiều - nhiều
			classA.getTeachers().add(teacher1);
			classA.getTeachers().add(teacher2);
			classB.getTeachers().add(teacher1);

			// // Lưu vào database
			// classroomRepository.saveAll(List.of(classA, classB));
			// teacherRepository.saveAll(List.of(teacher1, teacher2));
			// studentRepository.saveAll(List.of(student1, student2, student3));

			// Tạo thread pool để chạy đa luồng
			ExecutorService executor = Executors.newFixedThreadPool(3);

			// Chạy các tác vụ lưu dữ liệu đồng thời
			CompletableFuture<Void> saveClassrooms = CompletableFuture
					.runAsync(() -> classroomRepository.saveAll(List.of(classA, classB)), executor);

			CompletableFuture<Void> saveTeachers = CompletableFuture
					.runAsync(() -> teacherRepository.saveAll(List.of(teacher1, teacher2)), executor);

			CompletableFuture<Void> saveStudents = CompletableFuture
					.runAsync(() -> studentRepository.saveAll(List.of(student1, student2, student3)), executor);

			// Chờ tất cả các tác vụ hoàn thành
			CompletableFuture.allOf(saveClassrooms, saveTeachers, saveStudents).join();

			// Đóng thread pool
			executor.shutdown();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
