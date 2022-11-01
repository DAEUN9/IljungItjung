package a_service;

import com.iljungitjung.domain.category.dto.CategoryCreateRequestDto;
import com.iljungitjung.domain.category.dto.CategoryIdResponseDto;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationBlockRequestDto;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationIdResponseDto;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationRequestDto;
import com.iljungitjung.domain.schedule.dto.schedule.ScheduleViewDetailResponseDto;
import com.iljungitjung.domain.schedule.dto.schedule.ScheduleViewResponseDto;
import org.junit.jupiter.api.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("스케줄 서비스")
public class A_C_ScheduleServiceTest extends AbstractServiceTest{



    @Test
    @DisplayName("카테고리 등록")
    public void A() throws Exception {

        //given
        String categoryName = "커트3";
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
        String categoryName = "커트3";

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
    @DisplayName("일정 차단")
    public void C() throws Exception {

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
    @DisplayName("일정 리스트 조회")
    public void D() throws Exception {

        //given
        String nickname = "1";
        String startDate = "20221017";
        String endDate = "20221018";
        boolean isMyView = false;
        //when
        ScheduleViewResponseDto scheduleViewResponseDto = scheduleService.scheduleView(nickname, startDate, endDate, isMyView);

        //then
        int sum = scheduleViewResponseDto.getRequestList().size()+scheduleViewResponseDto.getBlockList().size()+scheduleViewResponseDto.getAcceptList().size()+scheduleViewResponseDto.getCancelList().size();

        Assertions.assertEquals(sum, scheduleId);


    }
    @Test
    @DisplayName("일정 상세 조회")
    public void E() throws Exception {
        //given

        //when
        ScheduleViewDetailResponseDto scheduleViewDetailResponseDto = scheduleService.scheduleViewDetail(scheduleId);

        //then
        Assertions.assertEquals(scheduleViewDetailResponseDto.getId(), scheduleId);
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
