package com.iljungitjung.domain.category.service;

import com.iljungitjung.domain.category.dto.CategoryCreateDto;
import com.iljungitjung.domain.category.dto.CategoryEditDto;

public interface CategoryService {
    void addCategory(CategoryCreateDto requestDto);
    void updateCategory(CategoryEditDto requestDto);
    void deleteCategory(int categoryId);
}
