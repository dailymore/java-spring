
package com.example.spring.classroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.spring.classroom.ClassroomEntity;

public interface ClassroomRepository extends JpaRepository<ClassroomEntity, Long> {

}
