package com.example.spring.teacher;

import java.util.ArrayList;
import java.util.List;

import com.example.spring.classroom.ClassroomEntity;
import com.example.spring.utils.JsonConverter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "teacher")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class TeacherEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@EqualsAndHashCode.Include
	@Column(nullable = false, length = 50)
	private String name;

	@Column()
	private String university;

	@Column()
	private int age;

	@Column()
	private int experience;

	@Column()
	private String phone;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(name = "subject", columnDefinition = "JSON")
	@Convert(converter = JsonConverter.class)
	private List<String> subjects;

	@Column(nullable = false)
	private String password;

	@ManyToMany(mappedBy = "teachers")
	private List<ClassroomEntity> classrooms = new ArrayList<>();

}
