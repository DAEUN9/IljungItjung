import com.iljungitjung.domain.category.dto.CategoryCreateRequestDto;
import com.iljungitjung.domain.category.dto.CategoryEditRequestDto;
import com.iljungitjung.domain.category.dto.CategoryIdResponseDto;
import com.iljungitjung.domain.category.service.CategoryService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class B_CategoryControllerTest extends AbstractControllerTest{

    @MockBean
    private CategoryService categoryService;

    @Test
    @Order(1)
    public void 카테고리_등록() throws Exception {

        //given
        CategoryCreateRequestDto requestDto = new CategoryCreateRequestDto("커트", "0130", "#000000");
        CategoryIdResponseDto responseDto = new CategoryIdResponseDto(categoryId+1);

        Mockito.when(categoryService.addCategory(any(CategoryCreateRequestDto.class))).thenReturn(responseDto);

        String requestAsString = objectMapper.writeValueAsString(requestDto);


        //when
        ResultActions actions = mockMvc.perform(post("/categories")
                .content(requestAsString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        //then
        actions.andDo(print())
                .andExpect(status().isOk());
        categoryId++;
    }
    @Test
    @Order(2)
    public void 카테고리_수정() throws Exception {

        //given
        CategoryEditRequestDto requestDto = new CategoryEditRequestDto(categoryId, "커트 수정", "0200", "#111111");
        CategoryIdResponseDto responseDto = new CategoryIdResponseDto(categoryId);

        Mockito.when(categoryService.updateCategory(any(CategoryEditRequestDto.class))).thenReturn(responseDto);

        String requestAsString = objectMapper.writeValueAsString(requestDto);


        //when
        ResultActions actions = mockMvc.perform(put("/categories")
                .content(requestAsString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        //then
        actions.andDo(print())
                .andExpect(status().isOk());

    }
    @Test
    @Order(3)
    public void 카테고리_삭제() throws Exception {


        //given
        CategoryIdResponseDto responseDto = new CategoryIdResponseDto(categoryId);

        Mockito.when(categoryService.deleteCategory(any(categoryId.getClass()))).thenReturn(responseDto);

        //when
        ResultActions actions = mockMvc.perform(delete("/categories/" + categoryId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        //then
        actions.andDo(print())
                .andExpect(status().isOk());

    }
}
