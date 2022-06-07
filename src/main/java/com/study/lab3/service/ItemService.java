package com.study.lab3.service;

import com.study.lab3.dto.ItemDto;

import java.util.List;

public interface ItemService {

    Long create(ItemDto itemDto);

    ItemDto get(Long id);

    List<ItemDto> getAll();
}
