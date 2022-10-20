package com.iljungitjung.domain.category.service;

import com.iljungitjung.domain.category.dto.CategoryCreateDto;
import com.iljungitjung.domain.category.dto.CategoryEditDto;
import com.iljungitjung.domain.category.entity.Category;
import com.iljungitjung.domain.category.exception.NoExistCategoryException;
import com.iljungitjung.domain.category.repository.CategoryRepository;
//import com.iljungitjung.domain.user.entity.User;
//import com.iljungitjung.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

//    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public void addCategory(CategoryCreateDto requestDto) {
        String categoryName = requestDto.getCategoryName();
        String time = requestDto.getTime();
        String color = requestDto.getColor();
//        User user = userRepository.findById(user_id);
//        Category category = new Category(user, categoryName, color, time);
        Category category = new Category(categoryName, color, time);
        categoryRepository.save(category);
    }

    @Override
    public void updateCategory(CategoryEditDto requestDto) {

        int categoryId = requestDto.getCategoryId();

        Category category = categoryRepository.findById(categoryId);
        if (category==null) {
            throw new NoExistCategoryException();
        }
        String categoryName = requestDto.getCategoryName();
        String color = requestDto.getColor();
        String time = requestDto.getTime();
        category.change(categoryName, color, time);
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(int categoryId) {
        Category category = categoryRepository.findById(categoryId);
        if (category==null) {
            throw new NoExistCategoryException();
        }
        categoryRepository.delete(category);
    }
}
