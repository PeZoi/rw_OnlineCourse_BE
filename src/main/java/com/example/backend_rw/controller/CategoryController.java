package com.example.backend_rw.controller;

import com.example.backend_rw.entity.dto.CategoryDTO;
import com.example.backend_rw.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list-all")
    public ResponseEntity<List<CategoryDTO>> listAllCategories(@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {
        List<CategoryDTO> categoryDTOS = categoryService.getAll(keyword);
        if (categoryDTOS.size() == 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categoryDTOS);
    }
}
