package service;

import com.iljungitjung.domain.category.dto.CategoryCreateRequestDto;
import com.iljungitjung.domain.category.dto.CategoryIdResponseDto;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationBlockRequestDto;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationIdResponseDto;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationRequestDto;
import com.iljungitjung.domain.schedule.dto.schedule.ScheduleViewDetailResponseDto;
import com.iljungitjung.domain.schedule.dto.schedule.ScheduleViewRequestDto;
import com.iljungitjung.domain.schedule.dto.schedule.ScheduleViewResponseDto;
import org.junit.jupiter.api.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class B_ScheduleServiceTest extends AbstractServiceTest{



    @Test
    @Order(1)
    public void 카테고리_등록_서비스() throws Exception {

        //given
        CategoryCreateRequestDto categoryCreateRequestDto = new CategoryCreateRequestDto(
                "커트", "0130", "#000000");

        //when
        CategoryIdResponseDto categoryIdResponseDto = categoryService.addCategory(categoryCreateRequestDto);

        //then
        categoryId++;
        System.out.println(categoryIdResponseDto.getId() + ", " + categoryId);
        Assertions.assertEquals(categoryIdResponseDto.getId(), categoryId);

    }

    @Test
    @Order(2)
    public void 일정_요청_서비스() throws Exception {

        //given
        ReservationRequestDto reservationRequestDto = new ReservationRequestDto("1", "2", "20221017", "1500", "안녕하세요", "01011111111", "커트");

        //when
        ReservationIdResponseDto reservationIdResponseDto = reservationService.reservationRequest(reservationRequestDto);

        //then
        scheduleId++;
        Assertions.assertEquals(reservationIdResponseDto.getId(), scheduleId);

    }

    @Test
    @Order(3)
    public void 일정_차단_요청_서비스() throws Exception {

        //given
        ReservationBlockRequestDto reservationBlockRequestDto = new ReservationBlockRequestDto("1", "공휴일", "공휴일이라서 쉽니다.", "20221017", "1500", "1630");

        //when
        ReservationIdResponseDto reservationIdResponseDto = reservationService.reservationBlock(reservationBlockRequestDto);

        //then
        scheduleId++;
        Assertions.assertEquals(reservationIdResponseDto.getId(), scheduleId);

    }

    @Test
    @Order(4)
    public void 일정_리스트_조회_서비스() throws Exception {

        //given

        //when
        ScheduleViewResponseDto scheduleViewResponseDto = scheduleService.scheduleView("1", new ScheduleViewRequestDto(true, "20221017", "20221017"));

        //then
        int sum = scheduleViewResponseDto.getRequestList().size()+scheduleViewResponseDto.getBlockList().size()+scheduleViewResponseDto.getAcceptList().size()+scheduleViewResponseDto.getCancelList().size();

        Assertions.assertEquals(sum, 4);


    }
    @Test
    @Order(5)
    public void 일정_상세_조회_서비스() throws Exception {
        //given

        //when
        ScheduleViewDetailResponseDto scheduleViewDetailResponseDto = scheduleService.scheduleViewDetail(scheduleId);

        //then
        Assertions.assertEquals(scheduleViewDetailResponseDto.getId(), scheduleId);
    }
    @Test
    @Order(6)
    public void 카테고리_삭제_서비스() throws Exception {
        //given

        //when
        CategoryIdResponseDto categoryIdResponseDto = categoryService.deleteCategory(categoryId);

        //then
        Assertions.assertEquals(categoryIdResponseDto.getId(), categoryId);

    }
}
