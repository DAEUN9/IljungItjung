package a_service;

import com.iljungitjung.domain.category.dto.CategoryCreateRequestDto;
import com.iljungitjung.domain.category.dto.CategoryIdResponseDto;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationBlockRequestDto;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationIdResponseDto;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationManageRequestDto;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationRequestDto;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("상담예약 서비스")
public class A_B_ReservationServiceTest extends AbstractServiceTest{


    @Test
    @DisplayName("카테고리 등록")
    public void A() throws Exception {
        //given
        String categoryName = "커트2";
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
    @DisplayName("일정 요청")
    public void B() throws Exception {

        //given
        String userFromNickname = "1";
        String userToNickname = "2";
        String date = "20221017";
        String startTime = "1500";
        String contents = "안녕하세요";
        String phone = "01011111111";
        String categoryName = "커트2";

        ReservationRequestDto reservationRequestDto = new ReservationRequestDto(
                userFromNickname, userToNickname, date,
                startTime, contents, phone,
                categoryName);
        //when
        ReservationIdResponseDto reservationIdResponseDto = reservationService.reservationRequest(reservationRequestDto);

        //then
        scheduleId++;
        Assertions.assertEquals(reservationIdResponseDto.getId(), scheduleId);

    }

    @Test
    @DisplayName("일정 수락")
    public void C() throws Exception {

        //given
        boolean accept = true;
        String reason = "가능합니다. 잘부탁드려요";
        String nickname = "1";

        ReservationManageRequestDto reservationManageRequestDto = new ReservationManageRequestDto(accept, reason);

        //when
        ReservationIdResponseDto reservationIdResponseDto = reservationService.reservationManage(scheduleId, nickname, reservationManageRequestDto);

        //then
        Assertions.assertEquals(reservationIdResponseDto.getId(), scheduleId);

    }

    @Test
    @DisplayName("일정 삭제")
    public void D() throws Exception {

        //given
        boolean accept = true;
        String reason = "가능합니다. 잘부탁드려요";
        String nickname = "1";

        ReservationManageRequestDto reservationManageRequestDto = new ReservationManageRequestDto(accept, reason);

        //when
        ReservationIdResponseDto reservationIdResponseDto = reservationService.reservationManage(scheduleId, nickname, reservationManageRequestDto);

        //then
        Assertions.assertEquals(reservationIdResponseDto.getId(), scheduleId);

    }

    @Test
    @DisplayName("일정 차단")
    public void E() throws Exception {

        //given
        String userFromNickname = "1";
        String title = "공휴일";
        String contents = "공휴일이라서 쉽니다.";
        String date = "20221017";
        String startTime = "1500";
        String endTime = "1630";

        ReservationBlockRequestDto reservationBlockRequestDto = new ReservationBlockRequestDto(
                userFromNickname, title, contents, date, startTime, endTime);
        //when
        ReservationIdResponseDto reservationIdResponseDto = reservationService.reservationBlock(reservationBlockRequestDto);

        //then
        scheduleId++;
        Assertions.assertEquals(reservationIdResponseDto.getId(), scheduleId);

    }

    @Test
    @DisplayName("카테고리 삭제")
    public void F() throws Exception {
        //given

        //when
        CategoryIdResponseDto categoryIdResponseDto = categoryService.deleteCategory(categoryId);

        //then
        Assertions.assertEquals(categoryIdResponseDto.getId(), categoryId);

    }

}
