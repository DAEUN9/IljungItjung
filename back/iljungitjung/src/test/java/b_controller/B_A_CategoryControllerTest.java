package b_controller;

import com.iljungitjung.domain.category.dto.CategoryCreateRequestDto;
import com.iljungitjung.domain.category.dto.CategoryEditRequestDto;
import com.iljungitjung.domain.category.dto.CategoryIdResponseDto;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("카테고리 컨트롤러")
public class B_A_CategoryControllerTest extends AbstractControllerTest{


//    @Test
//    @DisplayName("카테고리 등록")
//    public void A() throws Exception {
//
//        //given
//        String categoryName = "커트4";
//        String time = "0130";
//        String color = "#000000";
//
//        CategoryCreateRequestDto categoryCreateRequestDto = new CategoryCreateRequestDto(categoryName, time, color);
//        CategoryIdResponseDto categoryIdResponseDto = new CategoryIdResponseDto(categoryId+1);
//
//        Mockito.when(categoryService.addCategory(any(CategoryCreateRequestDto.class))).thenReturn(categoryIdResponseDto);
//
//        String requestAsString = objectMapper.writeValueAsString(categoryCreateRequestDto);
//
//
//        //when
//        ResultActions actions = mockMvc.perform(post("/categories")
//                .content(requestAsString)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON));
//
//        //then
//        actions.andDo(print())
//                .andExpect(status().isOk());
//        categoryId++;
//    }
//    @Test
//    @DisplayName("카테고리 수정")
//    public void B() throws Exception {
//
//        //given
//        String categoryName = "커트 수정4";
//        String time = "0200";
//        String color = "#111111";
//
//        CategoryEditRequestDto categoryEditRequestDto = new CategoryEditRequestDto(categoryId, categoryName, time, color);
//        CategoryIdResponseDto categoryIdResponseDto = new CategoryIdResponseDto(categoryId);
//
//        Mockito.when(categoryService.updateCategory(any(CategoryEditRequestDto.class))).thenReturn(categoryIdResponseDto);
//
//        String requestAsString = objectMapper.writeValueAsString(categoryEditRequestDto);
//
//
//        //when
//        ResultActions actions = mockMvc.perform(put("/categories")
//                .content(requestAsString)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON));
//
//        //then
//        actions.andDo(print())
//                .andExpect(status().isOk());
//
//    }
//    @Test
//    @DisplayName("카테고리 삭제")
//    public void 카테고리_삭제_컨트롤러() throws Exception {
//
//
//        //given
//        CategoryIdResponseDto categoryIdResponseDto = new CategoryIdResponseDto(categoryId);
//
//        Mockito.when(categoryService.deleteCategory(any(categoryId.getClass()))).thenReturn(categoryIdResponseDto);
//
//        //when
//        ResultActions actions = mockMvc.perform(delete("/categories/" + categoryId)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON));
//
//        //then
//        actions.andDo(print())
//                .andExpect(status().isOk());
//
//    }
}
