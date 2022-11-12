package com.iljungitjung.domain.category;

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
        String categoryName = "커트";
        String time = "0130";
        String color = "#000000";

        User user = User.builder().build();
        List<Category> categoryList = new ArrayList<>();

        user.setCategoryList(categoryList);

        CategoryCreateRequestDto categoryCreateRequestDto = new CategoryCreateRequestDto(
                categoryName, time, color);

        Category category = categoryCreateRequestDto.toEntity();
        category.setId(categoryId);

        //when
        when(userService.findUserBySessionId(httpSession)).thenReturn(user);
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        CategoryIdResponseDto categoryIdResponseDto = categoryService.addCategory(categoryCreateRequestDto, httpSession);

        //then
        Assertions.assertEquals(categoryIdResponseDto.getId(), 1L);

    }
    @Test
    @DisplayName("카테고리 수정")
    void updateCategory(){

        //given
        Long categoryId = 1L;
        String categoryName = "커트";
        String time = "0130";
        String color = "#000000";

        Long userId = 1L;
        User user = User.builder().build();
        user.setId(userId);

        Optional<Category> category = Optional.of(new Category(categoryName, color, time));
        category.get().setId(categoryId);
        category.get().setUser(user);

        CategoryEditRequestDto categoryEditRequestDto = new CategoryEditRequestDto(
                categoryId, categoryName, time, color);

        //when
        when(userService.findUserBySessionId(httpSession)).thenReturn(user);
        when(categoryRepository.findById(categoryId)).thenReturn(category);

        CategoryIdResponseDto categoryIdResponseDto = categoryService.updateCategory(categoryEditRequestDto, httpSession);
        //then
        Assertions.assertEquals(categoryIdResponseDto.getId(), 1L);

    }
    @Test
    @DisplayName("카테고리 수정시 해당 카테고리가 존재하지 않음")
    void noExistCategoryWhenUpdateCategoryExceptionTest(){
        //given
        Long categoryId = 1L;
        String categoryName = "커트";
        String time = "0130";
        String color = "#000000";

        CategoryEditRequestDto categoryEditRequestDto = new CategoryEditRequestDto(
                categoryId, categoryName, time, color);

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
        Long categoryId = 1L;
        String categoryName = "커트";
        String time = "0130";
        String color = "#000000";

        Long userId = 1L;
        User user = User.builder().build();
        user.setId(userId);

        Long errorUserId = 2L;
        User errorUser = User.builder().build();
        errorUser.setId(errorUserId);

        Optional<Category> category = Optional.of(new Category(categoryName, color, time));
        category.get().setId(categoryId);
        category.get().setUser(user);

        CategoryEditRequestDto categoryEditRequestDto = new CategoryEditRequestDto(
                categoryId, categoryName, time, color);


        //when
        when(userService.findUserBySessionId(httpSession)).thenReturn(errorUser);
        when(categoryRepository.findById(categoryId)).thenReturn(category);


        //then
        Assertions.assertThrows(NoGrantUpdateCategoryException.class, () -> {
            categoryService.updateCategory(categoryEditRequestDto, httpSession);
        });
    }

    @Test
    @DisplayName("카테고리 삭제")
    void deleteCategory(){

        //given
        Long categoryId = 1L;
        String categoryName = "커트";
        String time = "0130";
        String color = "#000000";

        Long userId = 1L;
        User user = User.builder().build();
        user.setId(userId);

        Optional<Category> category = Optional.of(new Category(categoryName, color, time));
        category.get().setId(categoryId);
        category.get().setUser(user);

        //when
        when(userService.findUserBySessionId(httpSession)).thenReturn(user);
        when(categoryRepository.findById(categoryId)).thenReturn(category);

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

        Long userId = 1L;
        User user = User.builder().build();
        user.setId(userId);

        Long errorUserId = 2L;
        User errorUser = User.builder().build();
        errorUser.setId(errorUserId);

        Optional<Category> category = Optional.of(new Category());
        category.get().setId(categoryId);
        category.get().setUser(user);

        //when
        when(userService.findUserBySessionId(httpSession)).thenReturn(errorUser);
        when(categoryRepository.findById(categoryId)).thenReturn(category);

        Assertions.assertThrows(NoGrantDeleteCategoryException.class, () -> {
            categoryService.deleteCategory(categoryId, httpSession);
        });
    }
}
