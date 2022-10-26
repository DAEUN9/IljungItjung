package com.iljungitjung.domain.category.service;

import com.iljungitjung.domain.category.dto.CategoryCreateRequestDto;
import com.iljungitjung.domain.category.dto.CategoryEditRequestDto;
import com.iljungitjung.domain.category.dto.CategoryIdResponseDto;
import com.iljungitjung.domain.category.entity.Category;
import com.iljungitjung.domain.category.exception.NoExistCategoryException;
import com.iljungitjung.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

//    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public CategoryIdResponseDto addCategory(CategoryCreateRequestDto requestDto) {
        Category category = requestDto.toCategoryEntity(requestDto);
        categoryRepository.save(category);
        return new CategoryIdResponseDto(category.getId());
    }

    @Override
    @Transactional
    public CategoryIdResponseDto updateCategory(CategoryEditRequestDto requestDto) {

        Long categoryId = requestDto.getId();
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> {
            throw new NoExistCategoryException();
        });
        Category updateCategory = requestDto.toCategoryEntity(requestDto);
        category.change(updateCategory);
        return new CategoryIdResponseDto(categoryId);
    }

    @Override
    @Transactional
    public CategoryIdResponseDto deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> {
            throw new NoExistCategoryException();
        });
        categoryRepository.delete(category);
        return new CategoryIdResponseDto(categoryId);
    }
}
