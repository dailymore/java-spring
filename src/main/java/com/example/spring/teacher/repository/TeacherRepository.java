package com.example.spring.teacher.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.spring.teacher.TeacherEntity;

public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {
	@Query("SELECT t FROM TeacherEntity t WHERE t.email = :username OR t.phone = :username")
	Optional<TeacherEntity> findOneByEmailOrPhone(String username);
}
