package com.example.backend_rw.service.impl;

import com.example.backend_rw.entity.Category;
import com.example.backend_rw.entity.dto.CategoryDTO;
import com.example.backend_rw.exception.CustomException;
import com.example.backend_rw.exception.NotFoundException;
import com.example.backend_rw.repository.CategoryRepository;
import com.example.backend_rw.service.CategoryService;
import com.example.backend_rw.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

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

    @Override
    public CategoryDTO get(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("Category ID không tồn tại"));

        return modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public CategoryDTO create(CategoryDTO categoryRequest) {
        // Kiểm tra xem đã tồn tại tên category đó chưa
        if (categoryRepository.existsCategoriesByName(categoryRequest.getName())) {
            throw new CustomException("Tên danh mục khóa học đã tồn tại!", HttpStatus.CONFLICT);
        }

        // Kiểm tra xem đã tồn tại slug đó chưa
        if (categoryRepository.existsCategoriesBySlug(categoryRequest.getSlug())) {
            throw new CustomException("Slug của danh mục khóa học đã từng tồn tại!", HttpStatus.CONFLICT);
        }

        Category category = modelMapper.map(categoryRequest, Category.class);

        // tạo slug theo tên (VD: khoá học java => khoa-hoc-java)
        String slug = Utils.removeVietnameseAccents(category.getName());

        category.setSlug(slug);

        Category savedCategory = categoryRepository.save(category);

        return modelMapper.map(savedCategory, CategoryDTO.class);
    }

    @Override
    public CategoryDTO update(Integer categoryId, CategoryDTO categoryRequest) {
        Category categoryInDB = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("Category ID không tồn tại"));

        Category category = categoryRepository.findByNameOrSlug(categoryRequest.getName(), categoryRequest.getSlug());

        if (category != null) {
            if (!Objects.equals(category.getId(), categoryInDB.getId())) {
                throw new CustomException("Tên/Slug của danh mục khóa học đã từng tồn tại!", HttpStatus.CONFLICT);
            }
        }

        String name = categoryRequest.getName();
        categoryInDB.setName(name);
        categoryInDB.setSlug(Utils.removeVietnameseAccents(name));
        Category updatedCategory = categoryRepository.save(categoryInDB);
        return modelMapper.map(updatedCategory, CategoryDTO.class);
    }

    @Override
    public String delete(Integer categoryId) {
        Category categoryInDB = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("Category ID không tồn tại"));

        categoryRepository.delete(categoryInDB);
        return "Xóa danh mục thành công";
    }
}
