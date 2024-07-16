package com.example.backend_rw.controller;

import com.example.backend_rw.entity.dto.CategoryDTO;
import com.example.backend_rw.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

    @GetMapping("/get/{id}")
    public ResponseEntity<CategoryDTO> get(@PathVariable(value = "id") Integer categoryId) {
        return ResponseEntity.ok(categoryService.get(categoryId));
    }

    @PostMapping("/create")
    public ResponseEntity<CategoryDTO> add(@RequestBody @Valid CategoryDTO categoryRequest) {
        CategoryDTO savedCategory = categoryService.create(categoryRequest);
        URI uri = URI.create("/api/categories/" + savedCategory.getId());

        return ResponseEntity.created(uri).body(savedCategory);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryDTO> updatedCategory(@PathVariable(value = "id") Integer categoryId, @RequestBody @Valid CategoryDTO categoryRequest) {
        return ResponseEntity.ok(categoryService.update(categoryId, categoryRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable(value = "id") Integer categoryId) {
        return ResponseEntity.ok(categoryService.delete(categoryId));
    }

}
