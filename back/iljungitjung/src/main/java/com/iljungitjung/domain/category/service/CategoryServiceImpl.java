package com.iljungitjung.domain.category.service;

import com.iljungitjung.domain.category.dto.CategoryListCreateRequestDto;
import com.iljungitjung.domain.category.dto.CategoryCreateRequestDto;
import com.iljungitjung.domain.category.dto.CategoryCreateResponseDto;
import com.iljungitjung.domain.category.entity.Category;
import com.iljungitjung.domain.category.repository.CategoryRepository;
import com.iljungitjung.domain.user.entity.User;
import com.iljungitjung.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    private final UserService userService;
    @Override
    @Transactional
    public CategoryCreateResponseDto addCategory(CategoryListCreateRequestDto requestDto, HttpSession httpSession) {
        Long count = 0L;
        User user = userService.findUserBySessionId(httpSession);

        for(Category category : user.getCategoryList()){
            categoryRepository.delete(category);
        }

        user.getCategoryList().clear();

        for(CategoryCreateRequestDto categoryRequestDto : requestDto.getCategoryList()){
            Category category = categoryRequestDto.toEntity();
            category.setCategoryList(user);
            categoryRepository.save(category);
            count++;
        }

        return new CategoryCreateResponseDto(count);
    }
}
