package com.iljungitjung.domain.category.service;

import com.iljungitjung.domain.category.dto.CategoryIdResponseDto;
import com.iljungitjung.domain.category.dto.CategoryCreateRequestDto;
import com.iljungitjung.domain.category.dto.CategoryEditRequestDto;

public interface CategoryService {
    CategoryIdResponseDto addCategory(CategoryCreateRequestDto requestDto);
    CategoryIdResponseDto updateCategory(CategoryEditRequestDto requestDto);
    CategoryIdResponseDto deleteCategory(Long id);
}
