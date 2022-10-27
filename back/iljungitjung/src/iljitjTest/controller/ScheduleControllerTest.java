import com.iljungitjung.domain.category.dto.CategoryCreateRequestDto;
import com.iljungitjung.domain.category.dto.CategoryIdResponseDto;
import com.iljungitjung.domain.category.service.CategoryService;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationBlockRequestDto;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationIdResponseDto;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationRequestDto;
import com.iljungitjung.domain.schedule.dto.schedule.ScheduleViewDetailResponseDto;
import com.iljungitjung.domain.schedule.dto.schedule.ScheduleViewRequestDto;
import com.iljungitjung.domain.schedule.dto.schedule.ScheduleViewResponseDto;
import com.iljungitjung.domain.schedule.entity.Schedule;
import com.iljungitjung.domain.schedule.service.ReservationService;
import com.iljungitjung.domain.schedule.service.ScheduleService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ScheduleControllerTest extends AbstractControllerTest{

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private ReservationService reservationService;

    @MockBean
    private ScheduleService scheduleService;

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
    public void 일정_요청() throws Exception {

        //given
        ReservationRequestDto requestDto = new ReservationRequestDto("1", "2", "20221017", "1500", "안녕하세요", "01011111111", "커트");
        ReservationIdResponseDto responseDto = new ReservationIdResponseDto(scheduleId+1);

        Mockito.when(reservationService.reservationRequest(any(ReservationRequestDto.class))).thenReturn(responseDto);

        String requestAsString = objectMapper.writeValueAsString(requestDto);


        //when
        ResultActions actions = mockMvc.perform(post("/reservations")
                .content(requestAsString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        //then
        actions.andDo(print())
                .andExpect(status().isOk());
        scheduleId++;
    }

    @Test
    @Order(3)
    public void 일정_차단_요청() throws Exception {

        //given
        ReservationBlockRequestDto requestDto = new ReservationBlockRequestDto("1", "공휴일", "공휴일이라서 쉽니다.", "20221017", "1500", "1630");
        ReservationIdResponseDto responseDto = new ReservationIdResponseDto(scheduleId+1);

        Mockito.when(reservationService.reservationBlock(any(ReservationBlockRequestDto.class))).thenReturn(responseDto);

        String requestAsString = objectMapper.writeValueAsString(requestDto);


        //when
        ResultActions actions = mockMvc.perform(post("/reservations/block")
                .content(requestAsString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        //then
        actions.andDo(print())
                .andExpect(status().isOk());
        scheduleId++;
    }

    @Test
    @Order(4)
    public void 일정_리스트_조회() throws Exception {

        //given
        ScheduleViewRequestDto requestDto = new ScheduleViewRequestDto("20221017", "20221017");
        String requestAsString = objectMapper.writeValueAsString(requestDto);


        //when
        ResultActions actions = mockMvc.perform(get("/schedules/nickname=1")
                .content(requestAsString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        //then
        actions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"));
    }
    @Test
    @Order(5)
    public void 일정_상세_조회() throws Exception {

        //given

        //when
        ResultActions actions = mockMvc.perform(get("/schedules/detail/"+scheduleId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        //then
        actions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"));

    }
    @Test
    @Order(6)
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
