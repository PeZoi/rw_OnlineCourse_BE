package com.example.backend_rw.service;

import com.example.backend_rw.entity.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAll(String keyword);

    CategoryDTO get(Integer categoryId);

    CategoryDTO create(CategoryDTO categoryRequest);

    CategoryDTO update(Integer categoryId, CategoryDTO categoryRequest);

    String delete(Integer categoryId);
}
