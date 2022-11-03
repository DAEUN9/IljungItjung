package controller;

import org.junit.jupiter.api.*;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("상담예약 컨트롤러")
public class ReservationControllerTest extends AbstractControllerTest{

//
//
//    @Test
//    @DisplayName("카테고리 등록")
//    public void A() throws Exception {
//
//        //given
//        String categoryName = "커트5";
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
//        String categoryName = "커트5";
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
//    @DisplayName("일정 수락")
//    public void C() throws Exception {
//
//        //given
//        boolean accept = true;
//        String reason = "가능합니다. 잘부탁드려요";
//
//        ReservationManageRequestDto reservationManageRequestDto = new ReservationManageRequestDto(accept, reason);
//        ReservationIdResponseDto reservationIdResponseDto = new ReservationIdResponseDto(scheduleId);
//
//        String nickname = "1";
//        Mockito.when(reservationService.reservationManage(any(scheduleId.getClass()), any(nickname.getClass()), any(ReservationManageRequestDto.class))).thenReturn(reservationIdResponseDto);
//
//        String requestAsString = objectMapper.writeValueAsString(reservationManageRequestDto);
//
//
//        //when
//        ResultActions actions = mockMvc.perform(put("/reservations/" + scheduleId + "?nickname=" + nickname)
//                .content(requestAsString)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON));
//
//        //then
//        actions.andDo(print())
//                .andExpect(status().isOk());
//    }
//
//
//    @Test
//    @DisplayName("일정 거절")
//    public void D() throws Exception {
//
//        //given
//        ReservationManageRequestDto reservationManageRequestDto = new ReservationManageRequestDto(false, "시간이 없어요");
//        ReservationIdResponseDto reservationIdResponseDto = new ReservationIdResponseDto(scheduleId);
//
//        String nickname = "1";
//        Mockito.when(reservationService.reservationManage(any(scheduleId.getClass()), any(nickname.getClass()), any(ReservationManageRequestDto.class))).thenReturn(reservationIdResponseDto);
//
//        String requestAsString = objectMapper.writeValueAsString(reservationManageRequestDto);
//
//
//        //when
//        ResultActions actions = mockMvc.perform(put("/reservations/" + scheduleId + "?nickname=" + nickname)
//                .content(requestAsString)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON));
//
//        //then
//        actions.andDo(print())
//                .andExpect(status().isOk());
//
//    }
//
//    @Test
//    @DisplayName("일정 차단")
//    public void E() throws Exception {
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
//                userFromNickname, title, contents, date, startTime, endTime);
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
//
//    }

}
