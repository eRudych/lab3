package com.study.lab3.dto;

import com.study.lab3.entity.Category;
import lombok.Builder;
import lombok.Data;

import java.util.Optional;

@Data
@Builder
public class CategoryDto {
    private Long id;
    private String name;
    private Long parentId;

    public static CategoryDto fromEntity(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .parentId(Optional.ofNullable(category.getParent()).map(Category::getId).orElse(null))
                .build();
    }
}
