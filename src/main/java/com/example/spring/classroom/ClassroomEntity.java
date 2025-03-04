package com.example.spring.classroom;

import java.util.ArrayList;
import java.util.List;

import com.example.spring.student.StudentEntity;
import com.example.spring.teacher.TeacherEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "classroom")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id") // ✅ Tránh vòng lặp vô hạn
public class ClassroomEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@Column(nullable = false, length = 50)
	private String name;

	@EqualsAndHashCode.Include
	@Column(nullable = false, length = 20)
	private String alias;

	@Column()
	private String description;

	@Column(nullable = false)
	private int size;

	@JsonManagedReference
	@OneToMany(mappedBy = "studentClass", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<StudentEntity> students = new ArrayList<StudentEntity>();

	@ManyToMany
	@JoinTable(name = "teacher_class", //
			joinColumns = @JoinColumn(name = "classroom_id"), //
			inverseJoinColumns = @JoinColumn(name = "teacher_id") //
	)
	private List<TeacherEntity> teachers = new ArrayList<>();
}
