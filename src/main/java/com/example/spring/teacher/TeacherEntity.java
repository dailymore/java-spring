package com.example.spring.teacher;

import java.util.ArrayList;
import java.util.List;

import com.example.spring.classroom.ClassroomEntity;
import com.example.spring.utils.JsonConverter;
// import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.databind.ObjectMapper;

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
public class TeacherEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@EqualsAndHashCode.Include
	@Column(nullable = false, length = 50)
	private String name;

	@Column()
	private String college;

	@Column()
	private int age;

	@Column()
	private int experience;

	@Column()
	private String phoneNumber;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(name = "subject", columnDefinition = "JSON")
	// @Convert(converter = JsonConverter.class)
	private List<String> subject;

	@Column(nullable = false)
	private String password;

	@ManyToMany(mappedBy = "teachers")
	private List<ClassroomEntity> classrooms = new ArrayList<>();

	// // Chỉ áp dụng với mysql
	// // Chuyển List<String> → JSON khi lưu vào database
	// public void setSubjects(List<String> subjects) {
	// ObjectMapper objectMapper = new ObjectMapper();
	// try {
	// this.subject = objectMapper.writeValueAsString(subjects);
	// } catch (JsonProcessingException e) {
	// throw new RuntimeException("JSON writing error", e);
	// }
	// }

	// // Chuyển JSON → List khi lấy dữ liệu
	// public List<?> getSubjects() {
	// ObjectMapper objectMapper = new ObjectMapper();

	// try {
	// return objectMapper.readValue(this.subject, List.class);
	// } catch (Exception e) {
	// throw new RuntimeException("JSON reading error", e);
	// }
	// }
}
