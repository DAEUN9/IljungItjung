import com.iljungitjung.domain.category.dto.CategoryCreateRequestDto;
import com.iljungitjung.domain.category.dto.CategoryEditRequestDto;
import com.iljungitjung.domain.category.dto.CategoryIdResponseDto;
import org.junit.jupiter.api.*;

import static org.mockito.ArgumentMatchers.any;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class A_CategoryServiceTest extends AbstractServiceTest{



    @Test
    @Order(1)
    public void 카테고리_등록() throws Exception {

        //given
        CategoryCreateRequestDto categoryCreateRequestDto = new CategoryCreateRequestDto(
                "커트", "0130", "#000000");

        //when
        CategoryIdResponseDto categoryIdResponseDto = categoryService.addCategory(categoryCreateRequestDto);

        //then
        categoryId++;
        System.out.println(categoryIdResponseDto.getId() + ", " + categoryId);
        Assertions.assertEquals(categoryIdResponseDto.getId(), categoryId);

    }
    @Test
    @Order(2)
    public void 카테고리_수정() throws Exception {


        //given
        CategoryEditRequestDto categoryEditRequestDto = new CategoryEditRequestDto(
                categoryId, "커트 수정", "0200", "#111111");

        //when
        CategoryIdResponseDto categoryIdResponseDto = categoryService.updateCategory(categoryEditRequestDto);

        //then
        Assertions.assertEquals(categoryIdResponseDto.getId(), categoryId);
    }
    @Test
    @Order(3)
    public void 카테고리_삭제() throws Exception {

        //given

        //when
        CategoryIdResponseDto categoryIdResponseDto = categoryService.deleteCategory(categoryId);

        //then
        Assertions.assertEquals(categoryIdResponseDto.getId(), categoryId);
    }


}
