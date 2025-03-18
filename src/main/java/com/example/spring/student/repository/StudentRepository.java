package com.example.spring.student.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.student.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
	Optional<StudentEntity> findByName(String name);

	Optional<StudentEntity> findOneByStudentId(String student);
}
