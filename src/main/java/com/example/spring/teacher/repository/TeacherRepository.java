package com.example.spring.teacher.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.teacher.TeacherEntity;

public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {

}
