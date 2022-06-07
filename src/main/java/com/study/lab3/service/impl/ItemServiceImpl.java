package com.study.lab3.service.impl;

import com.study.lab3.dto.ItemDto;
import com.study.lab3.entity.Category;
import com.study.lab3.entity.Item;
import com.study.lab3.repository.ItemRepository;
import com.study.lab3.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository repository;

    @Override
    public Long create(ItemDto itemDto) {
        return repository.save(new Item(itemDto.getId(),
                itemDto.getName(),
                new Category(itemDto.getCategoryId()),
                itemDto.getDescription(),
                itemDto.getPrice(),
                itemDto.getBnPrice(),
                itemDto.getUrl(),
                itemDto.getImage(),
                itemDto.getVendor())).getId();
    }

    @Override
    public ItemDto get(Long id) {
        return ItemDto.fromEntity(repository.findById(id).orElseThrow(RuntimeException::new));
    }

    @Override
    public List<ItemDto> getAll() {
        return repository.findAll()
                .stream()
                .map(ItemDto::fromEntity)
                .collect(Collectors.toList());
    }
}
