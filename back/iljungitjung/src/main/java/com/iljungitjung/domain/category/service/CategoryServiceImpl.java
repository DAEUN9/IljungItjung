package com.iljungitjung.domain.category.service;

import com.iljungitjung.domain.category.dto.CategoryListCreateRequestDto;
import com.iljungitjung.domain.category.dto.CategoryCreateDto;
import com.iljungitjung.domain.category.dto.CategoryCreateResponseDto;
import com.iljungitjung.domain.category.entity.Category;
import com.iljungitjung.domain.category.repository.CategoryRepository;
import com.iljungitjung.domain.user.entity.User;
import com.iljungitjung.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    private final UserService userService;
    @Override
    @Transactional
    public CategoryCreateResponseDto addCategory(CategoryListCreateRequestDto requestDto, HttpSession httpSession) {
        Long count = 0L;
        User user = userService.findUserBySessionId(httpSession);

        List<Category> categoryList = categoryRepository.findByUser_IdIs(user.getId());

        for(Category category : categoryList){
            categoryRepository.delete(category);
        }

        user.getCategoryList().clear();

        for(CategoryCreateDto categoryRequestDto : requestDto.getCategoryList()){
            Category category = categoryRequestDto.toEntity();
            category.setCategoryList(user);
            categoryRepository.save(category);
            count++;
        }

        return new CategoryCreateResponseDto(count);
    }
}
