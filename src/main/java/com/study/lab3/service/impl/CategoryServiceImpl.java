package com.study.lab3.service.impl;

import com.study.lab3.dto.CategoryDto;
import com.study.lab3.entity.Category;
import com.study.lab3.repository.CategoryRepository;
import com.study.lab3.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    @Override
    public CategoryDto create(CategoryDto dto) {
        if (dto.getParentId() != null) {
            return CategoryDto.fromEntity(repository.save(new Category(dto.getId(), new Category(dto.getParentId()), dto.getName())));
        } else {
            return CategoryDto.fromEntity(repository.save(new Category(dto.getId(), dto.getName())));
        }
    }

    @Override
    public CategoryDto get(Long id) {
        return CategoryDto.fromEntity(repository.findById(id).orElseThrow(RuntimeException::new));
    }

    @Override
    public List<CategoryDto> getAll() {
        return repository.findAll()
                .stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }
}
