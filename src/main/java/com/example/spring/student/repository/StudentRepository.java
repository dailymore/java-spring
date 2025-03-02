package com.example.spring.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.student.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

}
