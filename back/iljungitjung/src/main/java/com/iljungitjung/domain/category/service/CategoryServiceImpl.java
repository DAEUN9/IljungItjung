package com.iljungitjung.domain.category.service;

import com.iljungitjung.domain.category.dto.CategoryCreateRequestDto;
import com.iljungitjung.domain.category.dto.CategoryEditRequestDto;
import com.iljungitjung.domain.category.dto.CategoryIdResponseDto;
import com.iljungitjung.domain.category.entity.Category;
import com.iljungitjung.domain.category.exception.NoExistCategoryException;
import com.iljungitjung.domain.category.repository.CategoryRepository;
import com.iljungitjung.domain.user.entity.User;
import com.iljungitjung.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public CategoryIdResponseDto addCategory(CategoryCreateRequestDto requestDto) {
        Category category = requestDto.toCategoryEntity(requestDto);

        User user = userRepository.findUserByNickname("1").get();
        //세션 유저가 카테고리의 유저인지 검증
        /*

         */
        category.setCategoryList(user);
        category = categoryRepository.save(category);
        return new CategoryIdResponseDto(category.getId());
    }

    @Override
    @Transactional
    public CategoryIdResponseDto updateCategory(CategoryEditRequestDto requestDto) {

        Long categoryId = requestDto.getId();
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> {
            throw new NoExistCategoryException();
        });
        //세션 유저가 카테고리의 유저인지 검증
        /*

         */
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
        //세션 유저가 카테고리의 유저인지 검증
        /*

         */
       categoryRepository.delete(category);
        return new CategoryIdResponseDto(categoryId);
    }
}
