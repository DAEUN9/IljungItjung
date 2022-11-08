package com.iljungitjung.domain.category.service;

import com.iljungitjung.domain.category.dto.CategoryCreateRequestDto;
import com.iljungitjung.domain.category.dto.CategoryEditRequestDto;
import com.iljungitjung.domain.category.dto.CategoryIdResponseDto;
import com.iljungitjung.domain.category.entity.Category;
import com.iljungitjung.domain.category.exception.NoExistCategoryException;
import com.iljungitjung.domain.category.exception.NoGrantDeleteCategoryException;
import com.iljungitjung.domain.category.exception.NoGrantUpdateCategoryException;
import com.iljungitjung.domain.category.repository.CategoryRepository;
import com.iljungitjung.domain.user.entity.User;
import com.iljungitjung.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    private final UserService userService;
    @Override
    @Transactional
    public CategoryIdResponseDto addCategory(CategoryCreateRequestDto requestDto, HttpSession httpSession) {
        Category category = requestDto.toEntity();

        User user = userService.findUserBySessionId(httpSession);

        category.setCategoryList(user);
        category = categoryRepository.save(category);
        return new CategoryIdResponseDto(category.getId());
    }

    @Override
    @Transactional
    public CategoryIdResponseDto updateCategory(CategoryEditRequestDto requestDto, HttpSession httpSession) {

        Long categoryId = requestDto.getId();
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> {
            throw new NoExistCategoryException();
        });

        User user = userService.findUserBySessionId(httpSession);

        if(category.getUser().getId()!=user.getId()) throw new NoGrantUpdateCategoryException();

        Category updateCategory = requestDto.toEntity();
        category.change(updateCategory);
        return new CategoryIdResponseDto(categoryId);
    }

    @Override
    @Transactional
    public void deleteCategory(Long categoryId, HttpSession httpSession) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> {
            throw new NoExistCategoryException();
        });

        User user = userService.findUserBySessionId(httpSession);

        if(category.getUser().getId()!=user.getId()) throw new NoGrantDeleteCategoryException();

        categoryRepository.delete(category);
    }
}
