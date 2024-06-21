package com.example.backend_rw.service.impl;

import com.example.backend_rw.entity.Category;
import com.example.backend_rw.entity.dto.CategoryDTO;
import com.example.backend_rw.repository.CategoryRepository;
import com.example.backend_rw.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class CategoryServiceImpl implements CategoryService {
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(ModelMapper modelMapper, CategoryRepository categoryRepository) {
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> getAll(String keyword) {
        List<Category> categories = null;
        if (keyword.trim().equals("")) {
            categories = categoryRepository.search(keyword);
        } else {
            categories = categoryRepository.findAll();
        }
        return categories.stream().map(category -> {
            // Mapper về dạng CategoryEntity -> CategoryDTO
            return modelMapper.map(category, CategoryDTO.class);
        }).toList();
    }
}
