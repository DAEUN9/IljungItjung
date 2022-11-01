package a_service;

import com.iljungitjung.domain.category.dto.CategoryCreateRequestDto;
import com.iljungitjung.domain.category.dto.CategoryEditRequestDto;
import com.iljungitjung.domain.category.dto.CategoryIdResponseDto;
import org.junit.jupiter.api.*;

import static org.mockito.ArgumentMatchers.any;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("카테고리 서비스")
public class A_A_CategoryServiceTest extends AbstractServiceTest{



    @Test
    @DisplayName("카테고리 등록")
    public void A() throws Exception {

        //given
        String categoryName = "커트1";
        String time = "0130";
        String color = "#000000";

        CategoryCreateRequestDto categoryCreateRequestDto = new CategoryCreateRequestDto(
                categoryName, time, color);

        //when
        CategoryIdResponseDto categoryIdResponseDto = categoryService.addCategory(categoryCreateRequestDto);

        //then
        categoryId++;
        Assertions.assertEquals(categoryIdResponseDto.getId(), categoryId);

    }
    @Test
    @DisplayName("카테고리 수정")
    public void B() throws Exception {


        //given
        String categoryName = "커트 수정1";
        String time = "0200";
        String color = "#111111";

        CategoryEditRequestDto categoryEditRequestDto = new CategoryEditRequestDto(
                categoryId, categoryName, time, color);

        //when
        CategoryIdResponseDto categoryIdResponseDto = categoryService.updateCategory(categoryEditRequestDto);

        //then
        Assertions.assertEquals(categoryIdResponseDto.getId(), categoryId);
    }
    @Test
    @DisplayName("카테고리 삭제")
    public void C() throws Exception {

        //given

        //when
        CategoryIdResponseDto categoryIdResponseDto = categoryService.deleteCategory(categoryId);

        //then
        Assertions.assertEquals(categoryIdResponseDto.getId(), categoryId);
    }


}
