package com.iljungitjung.domain.category.service;

import com.iljungitjung.domain.category.dto.CategoryCreateRequestDto;
import com.iljungitjung.domain.category.dto.CategoryEditRequestDto;
import com.iljungitjung.domain.category.dto.CategoryIdResponseDto;
import com.iljungitjung.domain.category.entity.Category;
import com.iljungitjung.domain.category.exception.NoExistCategoryException;
import com.iljungitjung.domain.category.exception.NoGrantDeleteCategoryException;
import com.iljungitjung.domain.category.exception.NoGrantUpdateCategoryException;
import com.iljungitjung.domain.category.repository.CategoryRepository;
import com.iljungitjung.domain.category.service.CategoryService;
import com.iljungitjung.domain.category.service.CategoryServiceImpl;
import com.iljungitjung.domain.user.entity.User;
import com.iljungitjung.domain.user.repository.UserRepository;
import com.iljungitjung.domain.user.service.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("카테고리 서비스")
@ExtendWith(SpringExtension.class)
class CategoryServiceTest {

    private CategoryService categoryService;
    @MockBean
    private HttpSession httpSession;
    @MockBean
    private CategoryRepository categoryRepository;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private UserService userService;

    @BeforeEach
    void init(){
        categoryService = new CategoryServiceImpl(categoryRepository, userService);
    }

    @Test
    @DisplayName("카테고리 등록")
    void addCategory(){
        //given
        Long categoryId = 1L;
        String categoryName = "categoryName";
        String time = "0130";
        String color = "#000000";

        List<Category> categoryList = new ArrayList<>();

        User userFrom = createUserFrom();
        userFrom.setCategoryList(categoryList);

        CategoryCreateRequestDto categoryCreateRequestDto = new CategoryCreateRequestDto(
                categoryName, time, color);

        Category category = categoryCreateRequestDto.toEntity();
        category.setId(categoryId);

        //when
        when(userService.findUserBySessionId(any(HttpSession.class))).thenReturn(userFrom);
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        CategoryIdResponseDto categoryIdResponseDto = categoryService.addCategory(categoryCreateRequestDto, httpSession);

        //then
        Assertions.assertEquals(1L, categoryIdResponseDto.getId());

    }
    @Test
    @DisplayName("카테고리 수정")
    void updateCategory(){

        //given
        User userFrom = createUserFrom();

        Optional<Category> category = Optional.of(createCategory());
        category.get().setUser(userFrom);

        CategoryEditRequestDto categoryEditRequestDto = createEditCategory();

        //when
        when(userService.findUserBySessionId(any(HttpSession.class))).thenReturn(userFrom);
        when(categoryRepository.findById(any(Long.class))).thenReturn(category);

        CategoryIdResponseDto categoryIdResponseDto = categoryService.updateCategory(categoryEditRequestDto, httpSession);

        //then
        Assertions.assertEquals(1L, categoryIdResponseDto.getId());

    }
    @Test
    @DisplayName("카테고리 수정시 해당 카테고리가 존재하지 않음")
    void noExistCategoryWhenUpdateCategoryExceptionTest(){
        //given
        CategoryEditRequestDto categoryEditRequestDto = createEditCategory();

        //when

        //then
        Assertions.assertThrows(NoExistCategoryException.class, () -> {
            categoryService.updateCategory(categoryEditRequestDto, httpSession);
        });
    }
    @Test
    @DisplayName("카테고리 수정시 접속 유저가 카테고리 수정 권한이 없음")
    void noGrantUpdateCategoryWhenUpdateCategoryExceptionTest(){

        //given
        User userFrom = createUserFrom();
        User userTo = createUserTo();

        Optional<Category> category = Optional.of(createCategory());
        category.get().setUser(userFrom);

        CategoryEditRequestDto categoryEditRequestDto = createEditCategory();


        //when
        when(userService.findUserBySessionId(any(HttpSession.class))).thenReturn(userTo);
        when(categoryRepository.findById(any(Long.class))).thenReturn(category);


        //then
        Assertions.assertThrows(NoGrantUpdateCategoryException.class, () -> {
            categoryService.updateCategory(categoryEditRequestDto, httpSession);
        });
    }

    @Test
    @DisplayName("카테고리 삭제")
    void deleteCategory(){

        //given
        User userFrom = createUserFrom();

        Long categoryId = 1L;
        Optional<Category> category = Optional.of(createCategory());
        category.get().setUser(userFrom);

        //when
        when(userService.findUserBySessionId(any(HttpSession.class))).thenReturn(userFrom);
        when(categoryRepository.findById(any(Long.class))).thenReturn(category);

        categoryService.deleteCategory(categoryId, httpSession);

        //then

    }

    @Test
    @DisplayName("카테고리 삭제시 해당 카테고리가 존재하지 않음")
    void noExistCategoryWhenDeleteCategoryExceptionTest(){
        //given
        Long categoryId = 1L;

        Assertions.assertThrows(NoExistCategoryException.class, () -> {
            categoryService.deleteCategory(categoryId, httpSession);
        });
    }
    @Test
    @DisplayName("카테고리 삭제시 접속 유저가 카테고리 삭제 권한이 없음")
    void noGrantDeleteCategoryWhenDeleteCategoryExceptionTest(){

        //given
        Long categoryId = 1L;

        User userFrom = createUserFrom();
        User userTo = createUserTo();

        Optional<Category> category = Optional.of(createCategory());
        category.get().setUser(userFrom);

        //when
        when(userService.findUserBySessionId(any(HttpSession.class))).thenReturn(userTo);
        when(categoryRepository.findById(any(Long.class))).thenReturn(category);

        Assertions.assertThrows(NoGrantDeleteCategoryException.class, () -> {
            categoryService.deleteCategory(categoryId, httpSession);
        });
    }
    private Category createCategory(){
        Long categoryId = 1L;
        String categoryName = "categoryName";
        String categoryColor = "#000000";
        String time = "0130";

        Category category = Category.builder()
                .categoryName(categoryName)
                .color(categoryColor)
                .time(time)
                .build();
        category.setId(categoryId);

        return category;
    }
    private CategoryEditRequestDto createEditCategory(){
        Long updateCategoryId = 1L;
        String updateCategoryName = "updateCategoryName";
        String updateTime = "0100";
        String updateColor = "#111111";

        CategoryEditRequestDto categoryEditRequestDto = new CategoryEditRequestDto(
                updateCategoryId, updateCategoryName, updateTime, updateColor);

        return categoryEditRequestDto;
    }
    private User createUserFrom(){
        Long userFromId = 1L;

        User userFrom = User.builder().build();
        userFrom.setId(userFromId);

        return userFrom;
    }

    private User createUserTo(){
        Long userToId = 2L;

        User userTo = User.builder().build();
        userTo.setId(userToId);

        return userTo;
    }
}
