package com.iljungitjung.domain.category;

import com.iljungitjung.domain.category.dto.CategoryCreateRequestDto;
import com.iljungitjung.domain.category.dto.CategoryEditRequestDto;
import com.iljungitjung.domain.category.dto.CategoryIdResponseDto;
import com.iljungitjung.domain.category.entity.Category;
import com.iljungitjung.domain.category.repository.CategoryRepository;
import com.iljungitjung.domain.category.service.CategoryService;
import com.iljungitjung.domain.category.service.CategoryServiceImpl;
import com.iljungitjung.domain.user.entity.User;
import com.iljungitjung.domain.user.repository.UserRepository;
import com.iljungitjung.domain.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class CategoryServiceTest {

    private CategoryService categoryService;
    @MockBean
    protected HttpSession httpSession;
    @MockBean
    private CategoryRepository categoryRepository;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private UserService userService;

    @BeforeEach
    public void init(){
        categoryService = new CategoryServiceImpl(categoryRepository, userService);
    }

    @Test
    @DisplayName("카테고리 등록")
    public void A() throws Exception {
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

        Category category = categoryCreateRequestDto.toCategoryEntity(categoryCreateRequestDto);
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
    public void B() throws Exception {

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
    @DisplayName("카테고리 삭제")
    public void C() throws Exception {

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

        CategoryIdResponseDto categoryIdResponseDto = categoryService.deleteCategory(categoryId, httpSession);

        //then
        Assertions.assertEquals(categoryIdResponseDto.getId(), 1L);

    }


}
