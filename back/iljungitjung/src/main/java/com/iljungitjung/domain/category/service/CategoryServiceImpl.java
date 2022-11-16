package com.iljungitjung.domain.category.service;

import com.iljungitjung.domain.category.dto.CategoryCreateDto;
import com.iljungitjung.domain.category.dto.CategoryCreateResponseDto;
import com.iljungitjung.domain.category.dto.CategoryListCreateRequestDto;
import com.iljungitjung.domain.category.entity.Category;
import com.iljungitjung.domain.category.repository.CategoryRepository;
import com.iljungitjung.domain.user.entity.User;
import com.iljungitjung.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    private final UserService userService;

    @Override
    @Transactional
    public CategoryCreateResponseDto addCategory(CategoryListCreateRequestDto requestDto, HttpSession httpSession) {
        Long categoryCount = 0L;
        User user = userService.findUserBySessionId(httpSession);

        deleteCategoryListAtUser(user);
        putCategoryListToUser(requestDto, user);
        categoryCount = user.categoryCount();

        return new CategoryCreateResponseDto(categoryCount);
    }

    @Transactional
    private void deleteCategoryListAtUser(User user){
        List<Category> categoryList = categoryRepository.findByUser_IdIs(user.getId());
        categoryList.forEach(category -> categoryRepository.delete(category));
        user.clearCategoryList();
    }

    @Transactional
    private void putCategoryListToUser(CategoryListCreateRequestDto requestDto, User user){
        List<CategoryCreateDto> categoryList = requestDto.getCategoryList();
        categoryList.forEach(categoryRequestDto -> {
            Category category = categoryRequestDto.toEntity();
            category.updateCategoryList(user);
            categoryRepository.save(category);
        });
    }
}
