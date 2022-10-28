package controller;

import com.iljungitjung.domain.category.dto.CategoryCreateRequestDto;
import com.iljungitjung.domain.category.dto.CategoryIdResponseDto;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationBlockRequestDto;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationIdResponseDto;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationManageRequestDto;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationRequestDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class C_ReservationControllerTest extends AbstractControllerTest{



    @Test
    @Order(1)
    public void 카테고리_등록_컨트롤러() throws Exception {

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
    public void 일정_요청_컨트롤러() throws Exception {

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
    public void 일정_수락_컨트롤러() throws Exception {

        //given
        ReservationManageRequestDto requestDto = new ReservationManageRequestDto(true, "가능합니다. 잘 부탁드려요.");
        ReservationIdResponseDto responseDto = new ReservationIdResponseDto(scheduleId);

        Mockito.when(reservationService.reservationManage(any(scheduleId.getClass()), any(ReservationManageRequestDto.class))).thenReturn(responseDto);

        String requestAsString = objectMapper.writeValueAsString(requestDto);


        //when
        ResultActions actions = mockMvc.perform(put("/reservations/" + scheduleId)
                .content(requestAsString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        //then
        actions.andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    @Order(4)
    public void 일정_삭제_컨트롤러() throws Exception {

        //given
        ReservationManageRequestDto requestDto = new ReservationManageRequestDto(false, "시간이 없어요");
        ReservationIdResponseDto responseDto = new ReservationIdResponseDto(scheduleId);

        Mockito.when(reservationService.reservationManage(any(scheduleId.getClass()), any(ReservationManageRequestDto.class))).thenReturn(responseDto);

        String requestAsString = objectMapper.writeValueAsString(requestDto);


        //when
        ResultActions actions = mockMvc.perform(put("/reservations/" + scheduleId)
                .content(requestAsString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        //then
        actions.andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    @Order(5)
    public void 일정_차단_요청_컨트롤러() throws Exception {

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
    @Order(6)
    public void 카테고리_삭제_컨트롤러() throws Exception {

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
