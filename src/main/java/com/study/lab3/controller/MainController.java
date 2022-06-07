package com.study.lab3.controller;

import com.study.lab3.dto.CategoryDto;
import com.study.lab3.dto.ItemDto;
import com.study.lab3.service.CategoryService;
import com.study.lab3.service.ItemService;
import com.study.lab3.service.ParsingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/lab3")
@RequiredArgsConstructor
public class MainController {

    private final ParsingService parsingService;
    private final ItemService itemService;
    private final CategoryService categoryService;


    @PostMapping(value = "file/upload",
            consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public void uploadData(@RequestPart(value = "file") MultipartFile file) {
        parsingService.parseXML(file);
    }

    @GetMapping("items/{id}")
    public ItemDto getItem(@PathVariable("id") Long id) {
        return itemService.get(id);
    }

    @GetMapping("items")
    public List<ItemDto> getItems() {
        return itemService.getAll();
    }

    @GetMapping("categories")
    public List<CategoryDto> getCategories() {
        return categoryService.getAll();
    }

    @GetMapping("categories/{id}")
    public CategoryDto getCategory(@PathVariable("id") Long id) {
        return categoryService.get(id);
    }
}
