package controller;

import org.junit.jupiter.api.*;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("스케줄 컨트롤러")
public class ScheduleControllerTest{



//    @Test
//    @DisplayName("카테고리 등록")
//    public void A() throws Exception {
//
//        //given
//        String categoryName = "커트6";
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
//
//    @Test
//    @DisplayName("일정 요청")
//    public void B() throws Exception {
//
//        //given
//        String userFromNickname = "1";
//        String userToNickname = "2";
//        String date = "20221017";
//        String startTime = "1500";
//        String contents = "안녕하세요";
//        String phone = "01011111111";
//        String categoryName = "커트6";
//
//        ReservationRequestDto reservationRequestDto = new ReservationRequestDto(
//                userFromNickname, userToNickname, date,
//                startTime, contents, phone,
//                categoryName);
//        ReservationIdResponseDto reservationIdResponseDto = new ReservationIdResponseDto(scheduleId+1);
//
//        Mockito.when(reservationService.reservationRequest(any(ReservationRequestDto.class))).thenReturn(reservationIdResponseDto);
//
//        String requestAsString = objectMapper.writeValueAsString(reservationRequestDto);
//
//
//        //when
//        ResultActions actions = mockMvc.perform(post("/reservations")
//                .content(requestAsString)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON));
//
//        //then
//        actions.andDo(print())
//                .andExpect(status().isOk());
//        scheduleId++;
//    }
//
//    @Test
//    @DisplayName("일정 차단")
//    public void C() throws Exception {
//
//        //given
//        String userFromNickname = "1";
//        String title = "공휴일";
//        String contents = "공휴일이라서 쉽니다.";
//        String date = "20221017";
//        String startTime = "1500";
//        String endTime = "1630";
//
//        ReservationBlockRequestDto reservationBlockRequestDto = new ReservationBlockRequestDto(
//                userFromNickname, title, contents,
//                date, startTime, endTime);
//        ReservationIdResponseDto reservationIdResponseDto = new ReservationIdResponseDto(scheduleId+1);
//
//        Mockito.when(reservationService.reservationBlock(any(ReservationBlockRequestDto.class))).thenReturn(reservationIdResponseDto);
//
//        String requestAsString = objectMapper.writeValueAsString(reservationBlockRequestDto);
//
//
//        //when
//        ResultActions actions = mockMvc.perform(post("/reservations/block")
//                .content(requestAsString)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON));
//
//        //then
//        actions.andDo(print())
//                .andExpect(status().isOk());
//        scheduleId++;
//    }
//
//    @Test
//    @DisplayName("일정 리스트 조회")
//    public void D() throws Exception {
//
//        //given
//        String nickname = "1";
//        String startDate = "20221017";
//        String endDate = "20221018";
//        String isMyView = "false";
//
//        //when
//        ResultActions actions = mockMvc.perform(get("/schedules/nickname=" + nickname + "?startDate=" + startDate + "&endDate=" + endDate + "&isMyView=" + isMyView)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON));
//
//        //then
//        actions.andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.status").value("success"));
//    }
//    @Test
//    @DisplayName("일정 상세 조회")
//    public void E() throws Exception {
//
//        //given
//
//        //when
//        ResultActions actions = mockMvc.perform(get("/schedules/detail/"+scheduleId)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON));
//
//        //then
//        actions.andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.status").value("success"));
//
//    }
//    @Test
//    @DisplayName("카테고리 삭제")
//    public void F() throws Exception {
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
//    }
}
