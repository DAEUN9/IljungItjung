package com.iljungitjung.domain.category.service;

import com.iljungitjung.domain.category.dto.CategoryCreateRequestDto;
import com.iljungitjung.domain.category.dto.CategoryEditRequestDto;
import com.iljungitjung.domain.category.entity.Category;
import com.iljungitjung.domain.category.exception.NoExistCategoryException;
import com.iljungitjung.domain.category.repository.CategoryRepository;
//import com.iljungitjung.domain.user.entity.User;
//import com.iljungitjung.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

//    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public void addCategory(CategoryCreateRequestDto requestDto) {
        Category category = requestDto.toCategoryEntity(requestDto);
        System.out.println(category.toString());
        categoryRepository.save(category);
    }

    @Override
    public void updateCategory(CategoryEditRequestDto requestDto) {

        Long categoryId = requestDto.getCategoryId();

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> {
            throw new NoExistCategoryException();
        });
        Category updateCategory = requestDto.toCategoryEntity(requestDto);
        category.change(updateCategory);
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> {
            throw new NoExistCategoryException();
        });
        categoryRepository.delete(category);
    }
}
