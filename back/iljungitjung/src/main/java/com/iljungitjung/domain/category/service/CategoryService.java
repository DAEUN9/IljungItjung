package com.iljungitjung.domain.category.service;

import com.iljungitjung.domain.category.dto.CategoryCreateResponseDto;
import com.iljungitjung.domain.category.dto.CategoryListCreateRequestDto;

import javax.servlet.http.HttpSession;

public interface CategoryService {
    CategoryCreateResponseDto addCategory(CategoryListCreateRequestDto requestDto, HttpSession httpSession);

}
