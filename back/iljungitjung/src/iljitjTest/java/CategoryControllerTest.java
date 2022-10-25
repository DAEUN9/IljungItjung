import com.iljungitjung.domain.category.dto.CategoryCreateRequestDto;
import com.iljungitjung.domain.category.dto.CategoryEditRequestDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CategoryControllerTest extends AbstractControllerTest{


    @Test
    @Order(1)
    public void 카테고리_등록() throws Exception {

        String content = objectMapper.writeValueAsString(new CategoryCreateRequestDto(
                "커트", "0130", "#000000"));

        //given
        ResultActions actions = mockMvc.perform(post("/categories")
                .content(content)
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

        String content = objectMapper.writeValueAsString(new CategoryEditRequestDto(
                categoryId, "커트 수정", "0200", "#111111"));

        //given
        ResultActions actions = mockMvc.perform(put("/categories")
                .content(content)
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
        ResultActions actions = mockMvc.perform(delete("/categories/" + categoryId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        //then
        actions.andDo(print())
                .andExpect(status().isOk());
    }
}
