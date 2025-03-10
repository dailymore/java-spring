package com.example.spring.utils.dto.request;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.Data;

@Data
public class RelationsValidate {
	private List<String> relations;

	public <E extends Enum<E>> RelationsValidate(List<String> relations, Class<E> enumClass) {
		this.relations = relations;

		Set<String> validRelations = Arrays.stream(enumClass.getEnumConstants())
				.map(e -> {
					try {
						return (String) enumClass.getMethod("getValue").invoke(e);
					} catch (Exception ex) {
						return e.name(); // * Fallback nếu không có getValue() => name()
					}
				})
				.collect(Collectors.toSet());

		boolean isValid = relations.stream().allMatch(validRelations::contains);

		if (!isValid)
			throw new IllegalArgumentException("Relations không hợp lệ: " + relations);
	}
}
