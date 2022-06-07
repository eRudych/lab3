package com.study.lab3.service;

import com.study.lab3.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto create(CategoryDto dto);

    CategoryDto get(Long id);

    List<CategoryDto> getAll();

}
