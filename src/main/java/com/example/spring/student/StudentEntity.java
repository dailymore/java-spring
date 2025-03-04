package com.example.spring.student;

import com.example.spring.classroom.ClassroomEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "student")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class StudentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@EqualsAndHashCode.Include
	@Column(nullable = false, length = 50)
	private String name;

	@Column(nullable = false, unique = true)
	private String studentId;

	@Column(nullable = false, length = 20)
	private String classRoom;

	@Column()
	private String address;

	@Column(length = 3)
	private int age;

	@Column(length = 10)
	private float cpa;

	@Column()
	private String password;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "student_class_id")
	private ClassroomEntity studentClass;

}
