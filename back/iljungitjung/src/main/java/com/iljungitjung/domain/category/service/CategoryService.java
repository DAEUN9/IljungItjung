package com.iljungitjung.domain.category.service;

import com.iljungitjung.domain.category.dto.CategoryCreateRequestDto;
import com.iljungitjung.domain.category.dto.CategoryEditRequestDto;

public interface CategoryService {
    void addCategory(CategoryCreateRequestDto requestDto);
    void updateCategory(CategoryEditRequestDto requestDto);
    void deleteCategory(Long id);
}
