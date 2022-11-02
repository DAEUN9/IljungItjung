package com.iljungitjung.domain.category.service;

import com.iljungitjung.domain.category.dto.CategoryCreateRequestDto;
import com.iljungitjung.domain.category.dto.CategoryEditRequestDto;
import com.iljungitjung.domain.category.dto.CategoryIdResponseDto;
import com.iljungitjung.domain.category.entity.Category;
import com.iljungitjung.domain.category.exception.NoExistCategoryException;
import com.iljungitjung.domain.category.exception.NoGrantCategoryException;
import com.iljungitjung.domain.category.repository.CategoryRepository;
import com.iljungitjung.domain.user.entity.User;
import com.iljungitjung.domain.user.exception.NoExistUserException;
import com.iljungitjung.domain.user.repository.UserRepository;
import com.iljungitjung.global.login.entity.RedisUser;
import com.iljungitjung.global.login.exception.NotMemberException;
import com.iljungitjung.global.login.repository.RedisUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    private final RedisUserRepository redisUserRepository;


    @Override
    @Transactional
    public CategoryIdResponseDto addCategory(CategoryCreateRequestDto requestDto, HttpSession httpSession) {
        Category category = requestDto.toCategoryEntity(requestDto);

        RedisUser redisUser = redisUserRepository.findById(httpSession.getId()).orElseThrow(() -> {
            throw new NotMemberException();
        });;
        User user = userRepository.findUserByEmail(redisUser.getEmail()).orElseThrow(() -> {
            throw new NoExistUserException();
        });

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

        RedisUser redisUser = redisUserRepository.findById(httpSession.getId()).orElseThrow(() -> {
            throw new NotMemberException();
        });;
        User user = userRepository.findUserByEmail(redisUser.getEmail()).orElseThrow(() -> {
            throw new NoExistUserException();
        });

        if(category.getUser().getId()==user.getId()){
            Category updateCategory = requestDto.toCategoryEntity(requestDto);
            category.change(updateCategory);
        }else{
            throw new NoGrantCategoryException();
        }

        return new CategoryIdResponseDto(categoryId);
    }

    @Override
    @Transactional
    public CategoryIdResponseDto deleteCategory(Long categoryId, HttpSession httpSession) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> {
            throw new NoExistCategoryException();
        });

        RedisUser redisUser = redisUserRepository.findById(httpSession.getId()).orElseThrow(() -> {
            throw new NotMemberException();
        });;
        User user = userRepository.findUserByEmail(redisUser.getEmail()).orElseThrow(() -> {
            throw new NoExistUserException();
        });

        if(category.getUser().getId()==user.getId()){
            categoryRepository.delete(category);
        }else{
            throw new NoGrantCategoryException();
        }

        return new CategoryIdResponseDto(categoryId);
    }
}
