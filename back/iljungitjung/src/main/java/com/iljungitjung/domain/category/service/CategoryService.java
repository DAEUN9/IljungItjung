package com.iljungitjung.domain.category.service;

import com.iljungitjung.domain.category.dto.CategoryIdResponseDto;
import com.iljungitjung.domain.category.dto.CategoryCreateRequestDto;
import com.iljungitjung.domain.category.dto.CategoryEditRequestDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface CategoryService {
    CategoryIdResponseDto addCategory(CategoryCreateRequestDto requestDto, HttpSession httpSession);
    CategoryIdResponseDto updateCategory(CategoryEditRequestDto requestDto, HttpSession httpSession);
    void deleteCategory(Long id, HttpSession httpSession);
}
