import com.iljungitjung.IljungitjungApplication;
import com.iljungitjung.domain.category.dto.CategoryEditRequestDto;
import com.iljungitjung.domain.category.dto.CategoryIdResponseDto;
import com.iljungitjung.domain.category.dto.CategoryCreateRequestDto;
import com.iljungitjung.domain.category.entity.Category;
import com.iljungitjung.domain.category.repository.CategoryRepository;
import com.iljungitjung.domain.category.service.CategoryService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@RunWith(SpringRunner.class)
public class CategoryServiceTest extends AbstractServiceTest{

    @MockBean
    CategoryRepository categoryRepository;

    @Autowired
    CategoryService categoryService;

    @Test
    @Order(1)
    public void 카테고리_등록() throws Exception {
        //given
        CategoryCreateRequestDto categoryCreateRequestDto = new CategoryCreateRequestDto(
                "커트", "0130", "#000000");

        //when
        CategoryIdResponseDto categoryIdResponseDto = categoryService.addCategory(categoryCreateRequestDto);

        categoryId++;
        //then
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


        categoryId++;
        //then
        Assertions.assertEquals(categoryIdResponseDto.getId(), categoryId);
    }
    @Test
    @Order(3)
    public void 카테고리_삭제() throws Exception {

        //given

        //when
        CategoryIdResponseDto categoryIdResponseDto = categoryService.deleteCategory(categoryId);

        categoryId++;
        //then
        Assertions.assertEquals(categoryIdResponseDto.getId(), categoryId);
    }


}
