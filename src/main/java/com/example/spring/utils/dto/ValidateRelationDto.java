package com.example.spring.utils.dto;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.Getter;

@Getter
public class ValidateRelationDto {
	private List<String> relations;

	public ValidateRelationDto(List<String> relations) {
		this.relations = relations;
	}

	public <E extends Enum<E>> void validate(Class<E> enumClass) {

		Set<String> validRelations = Arrays.stream(enumClass.getEnumConstants())
				.map(e -> {
					try {
						return (String) enumClass.getMethod("getValue").invoke(e);
					} catch (Exception ex) {
						return e.name(); // * Fallback nếu không có getValue() => name()
					}
				})
				.collect(Collectors.toSet());

		boolean isValid = this.getRelations().stream().allMatch(validRelations::contains);

		if (!isValid)
			throw new IllegalArgumentException("Relations không hợp lệ: " + this.getRelations());
	}
}
